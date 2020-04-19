package com.pjq.service;

import com.pjq.dao.ChooseDao;
import com.pjq.dao.CourseDao;
import com.pjq.dao.FileDao;
import com.pjq.pojo.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author pjq
 */
@Service
public class FileService {
    @Resource
    private FileDao fileMapper;

    @Resource
    private CourseDao courseMapper;

    @Resource
    private ChooseDao chooseMapper;

    public String analyzeExcel(String userName, String fileName, MultipartFile file) {
        List<CourseExcel> courseExcels = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return "上传文件格式不正确";
        }
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                CourseExcel courseExcel = new CourseExcel();
                courseExcel.setCourseName(row.getCell(0).getStringCellValue());
                courseExcel.setContent(row.getCell(1).getStringCellValue());
                courseExcel.setSubject(row.getCell(2).getStringCellValue());
                courseExcel.setPrice((int) row.getCell(3).getNumericCellValue());
                courseExcel.setContentName(row.getCell(4).getStringCellValue());
                courseExcel.setSequence((int) row.getCell(5).getNumericCellValue());
                courseExcel.setLesson(row.getCell(6).getStringCellValue());
                courseExcel.setTime((int) row.getCell(7).getNumericCellValue());
                courseExcel.setDescription(row.getCell(8).getStringCellValue());
                courseExcel.setVideoUrl(row.getCell(9).getStringCellValue());
                courseExcels.add(courseExcel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        } catch (IllegalStateException e) {
//            e.printStackTrace();
            return "格式错误";
        } catch (NullPointerException e) {
            return "填写错误";
        }
        Collections.sort(courseExcels);
        boolean isCourseInsert = false;
        int maxS = 0;

        for (CourseExcel courseExcel : courseExcels) {
            Course course = new Course();
            course.setCourse_name(courseExcel.getCourseName());
            course.setTeacher(userName);
            course.setContent(courseExcel.getContent());
            course.setSubject(courseExcel.getSubject());
            course.setPrice(courseExcel.getPrice());
            course.setDescription(courseExcel.getDescription());
            course.setPurchased(0);
            course.setStar(0);
            Course course1 = courseMapper.checkCourse(courseExcel.getCourseName(), userName);
            if (course1 != null) {
                return "课程已存在";
            }
            if (!isCourseInsert) {
                fileMapper.upLoadCourseInfo(course);
                isCourseInsert = true;
            }
            List<Course> courses = courseMapper.selectByCourse(course.getCourse_name(), course.getContent(), course.getSubject());
            int courseId = courses.get(courses.size() - 1).getId();
            CourseContentList contentList = new CourseContentList();
            contentList.setCourse_id(courseId);
            contentList.setCourse_content_list(courseExcel.getContentName());
            contentList.setSequence(courseExcel.getSequence());
            if (maxS < contentList.getSequence()) {
                fileMapper.upLoadCourseContentList(contentList);
                maxS = contentList.getSequence();
            }

            Lecture lecture = new Lecture();
            lecture.setCourse_id(courseId);
            lecture.setContent_list_id(courseMapper.selectCourseContent(courseId, courseExcel.getContentName()).getId());
            lecture.setLesson(courseExcel.getLesson());
            lecture.setTimes(courseExcel.getTime());

            String url = "http://127.0.0.1:8088/" + userName + courseExcel.getVideoUrl();
            lecture.setUrl(url);
            lecture.setLikes(0);
            fileMapper.upLoadLecture(lecture);

        }
        return "success";
    }


    public String upLoadCourseVideo(String userName, MultipartFile file) {
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        if (!suffix.equals(".zip") && !suffix.equals(".rar")) {
            return "文件格式错误";
        }
        String unZipPath = "C:\\Users\\13457\\Desktop\\resources\\videos\\";
        File pushFile = new File(unZipPath + fileName);
        try {
            file.transferTo(pushFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (suffix.equals(".zip")) {
            unZipFiles(pushFile, unZipPath, userName);
        } else if (suffix.equals(".rar")) {

        } else {
            return "文件格式错误";
        }
        return "success";
    }

    public void unZipFiles(File file, String unZipPath, String userName) {

        try {
            ZipFile zipFile = new ZipFile(file, Charset.forName("GBK"));
            Iterator<ZipEntry> iteratorZip = (Iterator<ZipEntry>) zipFile.entries();
            while (iteratorZip.hasNext()) {
                ZipEntry entry = iteratorZip.next();
                String zipEntryName = entry.getName();
                String suffixName = zipEntryName.substring(zipEntryName.lastIndexOf("."));
                if (!suffixName.equals(".mp4")) {
                    continue;
                }
                InputStream inputStream = zipFile.getInputStream(entry);
//                File file1 = new File(unZipPath + userName + new Date().toString() + zipEntryName);
//                if (!file1.exists()) {
//                    file1.mkdirs();
//                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(unZipPath + userName + zipEntryName));
                int b;
                while ((b = inputStream.read()) != -1) {
                    bufferedWriter.write(b);
                }
                bufferedWriter.flush();
                inputStream.close();
                bufferedWriter.close();
                file.delete();
            }
        } catch (IOException e) {


        } catch (IllegalArgumentException e) {

        }
    }

    public void unRarFiles(MultipartFile file) {

    }

    public String upLoadHomeWork(MultipartFile file,int courseId,String userName){
        if (!file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
            return "上传文件格式不正确";
        }
        InputStream inputStream = null;
        List<Choose> chooses=new ArrayList<>();
        try {
            inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for(int r=1;r<=sheet.getLastRowNum();r++){
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                Choose choose=new Choose();
                choose.setTopic(row.getCell(0).getStringCellValue());
                choose.setA(row.getCell(1).getStringCellValue());
                choose.setB(row.getCell(2).getStringCellValue());
                choose.setC(row.getCell(3).getStringCellValue());
                choose.setD(row.getCell(4).getStringCellValue());
                choose.setAnswer(row.getCell(5).getStringCellValue());
                choose.setContent_list_id((int) row.getCell(6).getNumericCellValue());
                choose.setCourse_id(courseId);
                chooses.add(choose);
            }
            chooseMapper.insertIntoChoose(chooses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功";
    }

}
