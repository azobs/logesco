package com.sprintgether.script.management.server.scriptmanagement;

import com.sprintgether.script.management.server.scriptmanagement.dao.school.*;
import com.sprintgether.script.management.server.scriptmanagement.dao.user.RoleRepository;
import com.sprintgether.script.management.server.scriptmanagement.dao.user.StaffRepository;
import com.sprintgether.script.management.server.scriptmanagement.dao.user.TokenRepository;
import com.sprintgether.script.management.server.scriptmanagement.dao.user.UserRepository;
import com.sprintgether.script.management.server.scriptmanagement.model.school.*;
import com.sprintgether.script.management.server.scriptmanagement.model.user.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootApplication
public class ScriptManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(ScriptManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RoleRepository roleRepository,
                             UserRepository userRepository,
                             TokenRepository tokenRepository,
                             StaffRepository staffRepository,
                             InstitutionRepository institutionRepository,
                             SchoolRepository schoolRepository,
                             DepartmentRepository departmentRepository,
                             OptionRepository optionRepository,
                             LevelRepository levelRepository,
                             CourseRepository courseRepository,
                             CourseOutlineRepository courseOutlineRepository,
                             ModuleRepository moduleRepository,
                             ChapterRepository chapterRepository,
                             SectionRepository sectionRepository,
                             SubSectionRepository subSectionRepository,
                             ParagraphRepository paragraphRepository){
        return args->{
            ////Test de creation de la collection Role
            Role role=new Role(
                    "HOD",
                    "Role associe a un chef de departement"
            );
            Optional<Role> roleSaved = roleRepository.findRoleByRoleName(
                    role.getRoleName());

            if(roleSaved.isPresent()){
              System.out.println(roleSaved.get()+" already exist in DB");
            }
            else{
                roleRepository.save(role);
            }
            ////Test de creation de la collection User
            User user=new User(
                    "cedric",
                    "cedric",
                    true
            );
            Optional<User> userSaved = userRepository.findUserByUsername(
                    user.getUsername());
            if(userSaved.isPresent()){
                System.out.println(userSaved.get()+" already exist in DB");
            }
            else{
                User newUser = userRepository.save(user);
                List<Role> listofRole = roleRepository.findAll();
                newUser.setListofRole(listofRole);
                userRepository.save(newUser);
            }
            ////Test de creation de la collection Token
            Optional<User> userExist = userRepository.findUserByUsername(
                    "cedric");
            if(userExist.isPresent()) {
                Token token = new Token(
                        "ajhjhdsdsf55454dfdfdf",
                        new Date(),
                        EnumTokenType.ACCOUNT_ACTIVATION,
                        userExist.get()
                );
                Optional<Token> tokenSaved = tokenRepository.findTokenByValue(
                        token.getValue()
                );
                if(tokenSaved.isPresent()){
                    System.out.println(tokenSaved.get()+" already exist in DB");
                }
                else{
                    tokenRepository.save(token);
                }
            }
            ////Test de creation de la collection Staff
            //On a deja plus haut le userExist qui sera associe au staff
            if(userExist.isPresent()) {
                Staff staff = new Staff(
                        "azobs",
                        "dced",
                        "678470251",
                        "Douala Bekoko rue 5478",
                        "cckia@gmail.com",
                        "Enseignant dans une institution de la place",
                        EnumStaffType.LECTURER,
                        userExist.get()
                );
                Optional<Staff> staffSaved=staffRepository.findStaffByEmail(
                        staff.getEmail()
                );
                if(staffSaved.isPresent()){
                    System.out.println(staffSaved.get()+" already exist in DB");
                }
                else{
                    staffRepository.save(staff);
                }
            }
            ////Test de creation de la collection Institution
            Institution institution = new Institution(
                    "Institut Universitaire du Golfe de Guinee",
                    "IUG",
                    "blabla",
                    "Douala bonamoussadi",
                    "Avenue Saker"
            );
            Optional<Institution> institutionSaved =
                    institutionRepository.findInstitutionByName(
                            institution.getName()
                    );
            if(institutionSaved.isPresent()){
                System.out.println(institutionSaved.get()+" already exist in DB");
            }
            else{
                institutionRepository.save(institution);
            }
            ////Test de creation de la collection School
            Optional<Institution> institutionExist = institutionRepository.findInstitutionByName(
                    "Institut Universitaire du Golfe de Guinee"
            );
            if(institutionExist.isPresent()){
                Stream.of("SEAS SEAS School_of_applied_science",
                        "ISTDI ISTDI Ecole_d'ingenieur",
                        "PISTI PISTI Ecole_des_ingénieurs_francais")
                        .forEach(
                                schoolParam-> {
                                    School school = new School(
                                            schoolParam.split(" ")[0],
                                            schoolParam.split(" ")[1],
                                            schoolParam.split(" ")[2],
                                            institutionExist.get()
                                    );
                                    /*
                                    It is not possible to have 2 schools with the same name
                                    and acronyme in the same institution.
                                     */
                                    AtomicInteger canCreated = new AtomicInteger();
                                    institutionExist.get().getListofSchool().forEach(
                                            sc -> {
                                                if (sc.getName().equals(school.getName()) ||
                                                        sc.getAcronym().equals(school.getAcronym())) {
                                                    System.out.println(sc+" already exist in DB");
                                                    canCreated.set(1);
                                                }
                                            }
                                    );
                                    if(canCreated.intValue()==0){
                                        School savedSchool = schoolRepository.save(school);
                                        /*
                                        The school created must be added to the school list
                                        of the institution
                                         */
                                        institutionExist.get().getListofSchool().add(savedSchool);
                                        institutionRepository.save(institutionExist.get());
                                    }
                                }
                        );
            }

            ////Test de creation de la collection Department
            Optional<School> schoolExist = schoolRepository.findSchoolByName(
                    "SEAS"
            );
            Optional<Staff> staffExist = staffRepository.findStaffByEmail(
                    "cckia@gmail.com"
            );
            if(schoolExist.isPresent() && staffExist.isPresent()){
                //departmentRepository.save(department);
                Stream.of("HND_Industrial HNDI All_Industrial_HND",
                        "HND_Commerce HNDC All_Commerce_HND",
                        "Bachelor_Industrial BiTechI All_Bachelor_of_Technology")
                        .forEach(
                        deptParam->{
                            Department department = new Department(
                                    deptParam.split(" ")[0],
                                    deptParam.split(" ")[1],
                                    deptParam.split(" ")[2],
                                    schoolExist.get(),
                                    staffExist.get()
                            );
                            /*
                            It is not possible to have 2 department with the same name
                            and acronyme in the same school.
                             */
                            AtomicInteger canCreated = new AtomicInteger();
                            schoolExist.get().getListofDepartment().forEach(dept->{
                                    if(dept.getName().equals(department.getName()) ||
                                    dept.getAcronym().equals(department.getAcronym())){
                                        System.out.println(dept+" already exist in DB");
                                        canCreated.set(1);
                                    }
                                }
                            );
                            if(canCreated.intValue()==0){
                                Department savedDept = departmentRepository.save(department);
                                /*
                                The created department must be added to the school department list
                                 */
                                schoolExist.get().getListofDepartment().add(savedDept);
                                schoolRepository.save(schoolExist.get());
                            }
                        }
                );
            }
            ////Test de creation de Option dans un departement
            Optional<Department> deptExist = departmentRepository.findDepartmentByName(
                    "HND_Industrial"
            );
            if(deptExist.isPresent()){
                Stream.of("Software_Engineering SWE Field_of_study_to_developper",
                        "Network_Security NWS Field_of_study_to_be_network_engineer")
                        .forEach(optionParam->{
                            Option option = new Option(
                                    optionParam.split(" ")[0],
                                    optionParam.split(" ")[1],
                                    optionParam.split(" ")[2],
                                    deptExist.get()
                            );
                            /*
                            We cannot have 2 options with the same name in the
                            same department
                             */
                            AtomicInteger canCreated = new AtomicInteger();
                            deptExist.get().getListofOption().forEach(opt->{
                                        if(opt.getName().equals(option.getName()) ||
                                                opt.getAcronym().equals(option.getAcronym())){
                                            System.out.println(opt+" already exist in DB");
                                            canCreated.set(1);
                                        }
                                    }
                            );
                            if(canCreated.intValue()==0){
                                Option savedOption = optionRepository.save(option);
                                /*
                                We must add this new option to the department
                                option list
                                 */
                                deptExist.get().getListofOption().add(savedOption);
                                departmentRepository.save(deptExist.get());
                            }
                        });
            }
            ////Test de creation de level dans une Option
            Optional<Option> optExist = optionRepository.findOptionByName(
                    "Software_Engineering"
            );

            if(optExist.isPresent()){
                Stream.of("Level1 SWE1",
                        "Level2 SWE2")
                        .forEach(levelParam->{
                            Level level = new Level(
                                    levelParam.split(" ")[0],
                                    levelParam.split(" ")[1],
                                    optExist.get(),
                                    staffExist.get()
                            );
                            /*
                            We cannot have 2 levels with the same name in the
                            same option
                             */
                            AtomicInteger canCreated = new AtomicInteger();
                            optExist.get().getListofLevel().forEach(lev->{
                                        if(lev.getName().equals(level.getName()) ||
                                                lev.getAcronym().equals(level.getAcronym())){
                                            System.out.println(lev+" already exist in DB");
                                            canCreated.set(1);
                                        }
                                    }
                            );
                            if(canCreated.intValue()==0){
                                Level savedlevel = levelRepository.save(level);
                                /*
                                We must add this new level to the option
                                level list
                                 */
                                optExist.get().getListofLevel().add(savedlevel);
                                optionRepository.save(optExist.get());
                            }
                        });
            }




            ////Test de creation de Course
            Optional<Level> levelExist = levelRepository.findLevelByName(
                    "Level1"
            );
            if(levelExist.isPresent()){
                Stream.of("Software Testing & Quality Assurance __ STQA",
                        "Software Engineering __ SEng")
                        .forEach(
                                CourseParam->{
                                    List<Staff> lecturerList = new ArrayList<Staff>();
                                    lecturerList.add(staffExist.get());
                                    Course course = new Course(
                                            CourseParam.split("__")[0],
                                            CourseParam.split("__")[1],
                                            levelExist.get(),
                                            lecturerList
                                    );
                                    /*
                                    We cannot have 02 course with the same title
                                    in the same level
                                     */
                                    AtomicInteger canCreated = new AtomicInteger();
                                    levelExist.get().getListofCourse().forEach(c->{
                                                if(c.getTitle().equals(course.getTitle()) ||
                                                        c.getCourseCode().equals(course.getCourseCode())){
                                                    System.out.println(c+" already exist in DB");
                                                    canCreated.set(1);
                                                }
                                            }
                                    );
                                    if(canCreated.intValue()==0){
                                        Course savedCourse  = courseRepository.save(course);

                                        /*We must add this new Course to the level
                                        course list*/

                                        levelExist.get().getListofCourse().add(savedCourse);
                                        levelRepository.save(levelExist.get());
                                    }
                                }
                        );
            }

            ////Test de creation de CourseOutline
            Course courseExist = courseRepository.findAll().size()>0?
                    courseRepository.findAll().get(0):null;
            if(courseExist!=null){
                CourseOutline courseOutline = new CourseOutline(
                        "Programme de cours pour l'annee 2021/2022",
                        courseExist
                );
                courseOutlineRepository.save(courseOutline);
            }
            ////Test de creation de Module
            CourseOutline courseOutline = courseOutlineRepository.findAll().size()>0?
                    courseOutlineRepository.findAll().get(0):null;
            if(courseOutline!=null){
                Stream.of("Software Testing __ Software Quality")
                        .forEach(moduleParam ->{
                            Module module = new Module(
                                    moduleParam, 1,
                                    courseOutline
                            );
                            moduleRepository.save(module);
                        });
            }
            ////test de creation de Chapter
            ////Test de creation de Section
            ////Test de creation de SubSection
            ////Test de creation de Paragraph

            ////Test de creation d'une Question avec indication et content
            ////Test de creation d'une Question (type MCQ) avec indication, Proposition et content
            ////Test de creation de Answer pour chacune des questions
            ////Test de creation de 02 problem
            ////Test de creation de 02 Paper
        };
    }


}
