package com.example.FetchingData.Controller;

import com.example.FetchingData.DataBase.ClgStudent;
import com.example.FetchingData.DataBase.CreateClgStudent;
import com.example.FetchingData.DataBase.StudentRepository;
import com.example.FetchingData.RestObject.StudentRestObject;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/HomePage")
    public String  HomePage(){
        System.out.println("Akshay Dharpale");

        return "HomePage";
    }

    @PostMapping("/create")
    public String Create(@RequestBody CreateClgStudent CreClgStudent){
        System.out.println("we are inside the CREATE API");
        System.out.println(CreClgStudent);

        ClgStudent student = new ClgStudent();

        student.setName(CreClgStudent.getNameA());
        student.setMobileNumber(CreClgStudent.getMobileNumberA());
        student.setEmailId(CreClgStudent.getEmailIdA());
        student.setCity(CreClgStudent.getCityA());
        student.setDesignation(CreClgStudent.getDesignationA());
        student.setPassword(CreClgStudent.getPasswordA());



        studentRepository.save(student);

        return "every thing is working fine";
    }


    @PutMapping (value = "/update/{id}")
    public ResponseEntity<String>  Update (@RequestPart("profileImage") MultipartFile multipartFile,
                                           @RequestPart("studentObject") ClgStudent clgStudent) throws IOException {

        System.out.println("we are inside the UPDATE API");


        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getSize());

        System.out.println(clgStudent);
        Optional<ClgStudent> optional = studentRepository.findById(clgStudent.getId());
        ClgStudent student = optional.get();

        Date date = new Date();
        long dateLong = date.getTime();


        if(multipartFile != null){

            String path_Directory = "D:\\desktop\\UDEMY\\FullStack projects React + SpringBoot\\Project 1\\ContactManager\\my-app\\src\\ProfileImages";
            Path path = Paths.get(path_Directory + File.separator
                    + dateLong + multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

            String imageName = dateLong + multipartFile.getOriginalFilename();
            
            System.out.println(student.getImageName());

            student.setImageName(imageName);


        }

        if(clgStudent.getName() != null){
            student.setName(clgStudent.getName());

        }
        if(clgStudent.getCity() != null){
            student.setCity(clgStudent.getCity());

        }
        if(clgStudent.getEmailId() != null){
            student.setEmailId(clgStudent.getEmailId());

        }
        if(clgStudent.getDesignation() != null){
            student.setDesignation(clgStudent.getDesignation());

        }
        if(clgStudent.getPassword() != null){
            student.setPassword(clgStudent.getPassword());

        }
        if(clgStudent.getMobileNumber() != null){
            student.setMobileNumber(clgStudent.getMobileNumber());

        }

        this.studentRepository.save(student);


        return ResponseEntity.ok("updated successfully");
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String>  Delete  (@PathVariable int id){
        System.out.println("we are inside the DELETE API");




        try{
            Optional<ClgStudent> option = this.studentRepository.findById(id);
            ClgStudent student  = option.get();

            System.out.println(student);

            this.studentRepository.deleteById(id);
            return ResponseEntity.of(Optional.of("Object Delete Successfully"));

        }catch(Exception e){
            System.out.println("Exception occur");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id not found");

        }

    }

    //////////////////////////Get Mapping All using pagination//////////////////////////


    @GetMapping ("/receive")
    public ResponseEntity<StudentRestObject>   ReceiveDefaultAll (){
//        System.out.println("we are inside the RECEIVED API");

        Pageable pageable = PageRequest.of(0, 3);

        List<ClgStudent> studentsList = this.studentRepository.findAll(pageable);

        Iterable<ClgStudent> TotalStudentList = this.studentRepository.findAll();
        List<ClgStudent> allStudents = new ArrayList<>();
        TotalStudentList.forEach(allStudents::add);

        StudentRestObject student = new StudentRestObject();
        student.setTotalResult(allStudents.size());
        student.setRestStudentList(studentsList);

        return ResponseEntity.of(Optional.of(student));
    }

    @GetMapping ("/receive/{id}")
    public ResponseEntity<ClgStudent>  Receive (@PathVariable int id){
        System.out.println("we are inside the RECEIVED API");

        Optional<ClgStudent> optional = studentRepository.findById(id);
        ClgStudent student = optional.get();

        return ResponseEntity.of(Optional.of(student));
    }


    @GetMapping ("/receive/pageSize={pageSize}")
    public ResponseEntity<List<ClgStudent>>  ReceiveByPageSize (@PathVariable int pageSize){
        System.out.println("we are inside the RECEIVED API by using page number");

        Pageable pageable = PageRequest.of(0, pageSize);

        List<ClgStudent> studentsList = this.studentRepository.findAll(pageable);

        for (ClgStudent student:studentsList) {
            System.out.println(student.toString());
        }


        return ResponseEntity.of(Optional.of(studentsList));
    }

    @GetMapping ("/receive/pageNumber={pageNumber}")
    public ResponseEntity<StudentRestObject>  ReceiveByPageNumber (@PathVariable int pageNumber){
        System.out.println("we are inside the RECEIVED API by using page number");

        Pageable pageable = PageRequest.of(pageNumber - 1, 3);

        List<ClgStudent> studentsList = this.studentRepository.findAll(pageable);

        Iterable<ClgStudent> TotalStudentList = this.studentRepository.findAll();
        List<ClgStudent> allStudents = new ArrayList<>();
        TotalStudentList.forEach(allStudents::add);

        StudentRestObject student = new StudentRestObject();
        student.setTotalResult(allStudents.size());
        student.setRestStudentList(studentsList);


        return ResponseEntity.of(Optional.of(student));
    }



    @GetMapping ("/receive/pageNumber={pageNumber}/pageSize={pageSize}")
    public ResponseEntity<List<ClgStudent>>  ReceiveByPageNumberAndPageSize (@PathVariable int pageSize, @PathVariable int pageNumber ){
        System.out.println("we are inside the RECEIVED API by using page number");

        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        List<ClgStudent> studentsList = this.studentRepository.findAll(pageable);

        for (ClgStudent student:studentsList) {
            System.out.println(student.toString());
        }

        return ResponseEntity.of(Optional.of(studentsList));
    }

    @GetMapping ("/searchRelative/{keyword}")
    @CrossOrigin
    public List<ClgStudent>  searchRelative(@PathVariable String keyword){
        System.out.println("We are inside the Search relative");
        List<ClgStudent> list = this.studentRepository.findByNameContaining(keyword);
//        for (ClgStudent student: list) {
//            System.out.println(student);
//        }


        return list;
    }


}
