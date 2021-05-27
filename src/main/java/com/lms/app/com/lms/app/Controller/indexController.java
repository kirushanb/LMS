package com.lms.app.com.lms.app.Controller;

import com.lms.app.com.lms.app.model.*;

import com.lms.app.com.lms.app.repo.*;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.JOptionPane;

@Controller
public class indexController {

    @Autowired
    studentRepo studentRepo;

    @Autowired
    lectureRepo lectureRepo;

    @Autowired
    eventRepo eventRepo;

    @Autowired
    subjectRepo subjectRepo;

    @Autowired
    marksRepo marksRepo;

    @Autowired
    PositiveRepo positiveRepo;

    @Autowired
    negativeRepo negativeRepo;

    @Autowired
    MiddleRepo middleRepo;

    @Autowired
    InqRepo inqrepo;

    @Autowired
    Adminrepo adminrepo;

    @Autowired
    disRepo drepo;


    //Home page mapping MVC
    @RequestMapping("/")
    public String lmsPage() {

        return "myhome";
    }


    @RequestMapping("/sci.admin")
    public String adminPage() {

        return "adminlog";
    }

    /*Admin loging controller
     * check admin username and password and allowed to admin control's
     */
    @RequestMapping("/logadmindash")
    public String logToAdmin(@RequestParam String Username, String Password) {

        if (adminrepo.findUser(Username) == null) {
            return "adminlog";
        } else {

            Admin_account account = adminrepo.findUser(Username);
            String pass = account.getPassword();
            if (pass.equals(Password)) {
                return "admin";
            }
        }

        return "adminlog";


    }


    //admin addstudent-page view student data
    @RequestMapping("/showstudent")
    public String studentPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("data", studentRepo.findAll(new PageRequest(page, 3)));
        model.addAttribute("currentPage", page);

        return "addstudentdata";
    }

    //Admin add new student
    @PostMapping("/savestudent")
    public String addStudent(Students students) {

        studentRepo.save(students);
        //  JOptionPane.showMessageDialog(null, "add sucess");
        return "redirect:/showstudent";

    }

    //admin delete student
    @GetMapping("/deletestudents")
    public String deleteStudent(int id) {
        studentRepo.deleteById(id);
        return "redirect:/showstudent";

    }

    //admin find student by id
    @GetMapping("/findstudents")
    @ResponseBody
    public Optional<Students> findeStudent(int id) {
        return studentRepo.findById(id);


    }

    @GetMapping("/findone")
    @ResponseBody
    public Students findstude(int id) {
        return studentRepo.getOne(id);
    }


    @RequestMapping("/adddata")
    public String addstdata() {
        return "addstudentdata";
    }


    //admin-> view of lectures data
    @RequestMapping("/showlecture")
    public String lecturepage(Model model, @RequestParam(defaultValue = "0") int page) {

        model.addAttribute("data", lectureRepo.findAll(new PageRequest(page, 3)));
        model.addAttribute("currentPage", page);
        return "lecture";
    }

    //Admin-> add new lecture
    @PostMapping("/addlecture")
    public String saveLactur(Lecture lecture) {

        lectureRepo.save(lecture);
        return "redirect:/showlecture";

    }

    @GetMapping("/deletelecture")
    public String deleteLecture(int id) {

        lectureRepo.deleteById(id);
        return "redirect:/showlecture";
    }

    @GetMapping("/findlecture")
    @ResponseBody
    public Optional<Lecture> findLecture(int id) {

        return lectureRepo.findById(id);
    }

    //event page view controller MVC
    @RequestMapping("/evnt")
    public String showevents() {

        return "events";
    }

    @PostMapping("/addevent")
    public String addevents(Event event, Model model) {

        eventRepo.save(event);
        model.addAttribute("sucess", "Events successfully added!");
        // return "redirect:/evnt";
        return "events";
    }


    @RequestMapping("/showsubject")
    public String showSubjectPage() {
        return "subject";
    }


    @PostMapping("/addsubjrct")
    public String addsubjecttoDb(subjects subjects, Model model) {

        subjectRepo.save(subjects);
        model.addAttribute("sucess", "Subject add success!");
        return "subject";
    }

//    @RequestMapping("/logl")
//    public  String logLecture(){
//
//       return "logingl";
//    	
//    }

    //Lectures  login controller and config
    @RequestMapping("/leclog")
    public String lectureLog(Model model, String Username, String Password) {

        if (lectureRepo.findbylog(Username) == null) {
            System.out.println("invalid username");
            return "myhome";
        }

        Lecture lecture = lectureRepo.findbylog(Username);
        String user = lecture.getUsername();
        String pass = lecture.getPassword();

        if (user.equals(Username) && pass.equals(Password)) {

            int id = lecture.getId();
            List<subjects> subjects = subjectRepo.findByLid(id);

            //   Optional<Positive> positive=positiveRepo.findById(id);

            List<Positive> positive = positiveRepo.findallbyPositive(id);
            List<Negative> negatives = negativeRepo.findallbyPositive(id);
            List<MiddleComments> middleComments = middleRepo.findallbyMiddle(id);

            model.addAttribute("events", eventRepo.findAll());
            model.addAttribute("data", subjects);
            model.addAttribute("ldata", lecture);
            model.addAttribute("feed", positive);
            model.addAttribute("neg", negatives);
            model.addAttribute("mid", middleComments);
            System.out.println(positive);


            return "lecturelog2";
        }
        return "myhome";

    }


    //show events on lecture dashboard 
    @RequestMapping("/getevnt")
    public String getEvents() {

        List<Event> event = eventRepo.findAll();
        System.out.println(" events is" + event);
        return "lecturelog";
    }

    @RequestMapping("/showmarks")
    private String showMarksPage(Model model, @RequestParam(defaultValue = "0") int page) {

        model.addAttribute("marks", marksRepo.findAll(new PageRequest(page, 3)));
        model.addAttribute("currentpage", page);

        return "addmarks";

    }

    @RequestMapping("/addstmarks")
    public String addMarks(Marks marks) {

        marksRepo.save(marks);

        return "redirect:/showmarks";
    }


    @GetMapping("/findmarks")
    @ResponseBody
    public Optional<Marks> findMarks(int marksid) {

        return marksRepo.findById(marksid);


    }


    @GetMapping("/deletemarks")
    public String delMarks(int marksid) {

        marksRepo.deleteById(marksid);

        return "redirect:/showmarks";
    }

//  @RequestMapping("/stlg")
//  public String studentLog(){
//
//        return "studentloging";
//  }

//    
//  @RequestMapping("/stdash")
//  public String studentDashbord(int id,String Password,Model model){
//
//  Students students=studentRepo.findByCustom(id);
//  
//  int sid=students.getId();
//  String pss=students.getPassword();
//  if(sid==id && pss.equals(Password)) {
//
//      Marks marks=marksRepo.findBycustom(id);
//
//
//  model.addAttribute("evnt",eventRepo.findAll());
//
//  model.addAttribute("stu",students);
//  model.addAttribute("mark",marks);
//
//
// System.out.print("marks is" +marks);
//
//
//   return "stloging";
//  }
//  else {
//	  
//	  return  "redirect:/stlg";
//  }
//  }


    //login  for student

    //student login controller and config

    @RequestMapping("/logstudent")
    public String logst(String Username, String Password, Model model) {

        System.out.println(Username);
        if (studentRepo.findbylog(Username) == null) {
            System.out.println("invalid Username");
            return "myhome";

        } else {


            Students students = studentRepo.findbylog(Username);
            String usr = students.getUsername();
            String pass = students.getPassword();
            if (pass.equals(Password)) {
                System.out.println(Username);
                int id = students.getId();


                Marks marks = marksRepo.findBycustom(id);


                model.addAttribute("evnt", eventRepo.findAll());
                List<subjects> subjects = subjectRepo.findAll();

                model.addAttribute("stu", students);
                model.addAttribute("mark", marks);
                model.addAttribute("data", subjects);

                return "stloging";
            }


        }
        return "myhome";


    }

    //this is the url for call addfeedback page
    @RequestMapping("/feedbk")
    public String showFeedback() {

        return "feedback";
    }


    @RequestMapping("/addfeedbk")
    public String addFeedback(@RequestParam String Username, String Password) {

        if (studentRepo.findbylog(Username) == null) {

            return "myhome";
        } else {
            Students students = studentRepo.findbylog(Username);
            String pass = students.getPassword();
            if (pass.equals(Password)) {

                return "addfeedback";
            }
        }
        return "myhome";
    }

    @RequestMapping("/getLectureName")
    public String getLectureName(@RequestParam int id, Model model) {


        Lecture lecture = lectureRepo.findBycCusId(id);


        System.out.println(lecture);
        model.addAttribute("data", lecture);
        return "/addfeedback";

    }


    /*add feedback controller
     *
     * using NLP
     * sentiment analysis
     */

    @RequestMapping("/addfeed")
    public String addFeedback(@RequestParam String message, Positive positive, Negative negative, MiddleComments middleComments, Model model, discard discard) throws IOException {

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = message;
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> sentences = coreDocument.sentences();


//        for (CoreSentence sentence : sentences) {
//
//            String sentiment = sentence.sentiment();
//            System.out.println(sentiment + "= " + sentence);
//            if (sentiment.equals("Positive") || sentence.equals("Very positive")) {
//
//                System.out.println("positive comment");
//                positiveRepo.save(positive);
//                model.addAttribute("sucess", "Thank you for your feedback");
//
//
//            } else if (sentiment.equals("Negative") || sentence.equals("Very negative")) {
//
//                System.out.println("negative comment");
//                negativeRepo.save(negative);
//                model.addAttribute("sucess", "Thank you for your feedback");
//            } else if (sentiment.equals("Neutral")) {
//                System.out.println("middle comment");
//                middleRepo.save(middleComments);
//                model.addAttribute("sucess", "Thank you for your feedback");
//
//            } else {
//                drepo.save(discard);
//                model.addAttribute("sucess", "Unknown feedback");
//            }
//        }
            String sentiment = mainClass(message);
//            System.out.println(sentiment + "= " + sentence);
            if (sentiment.equals("Positive") ) {

                System.out.println("positive comment");
                positiveRepo.save(positive);
                model.addAttribute("sucess", "Thank you for your feedback");


            } else if (sentiment.equals("Negative")) {

                System.out.println("negative comment");
                negativeRepo.save(negative);
                model.addAttribute("sucess", "Thank you for your feedback");
            } else if (sentiment.equals("Neutral")) {
                System.out.println("middle comment");
                middleRepo.save(middleComments);
                model.addAttribute("sucess", "Thank you for your feedback");

            } else {
                drepo.save(discard);
                model.addAttribute("sucess", "Unknown feedback");
            }
        return "addfeedback";
        // return "nsbm";


    }
    public String mainClass(String reviews) throws IOException {

        Set<String> vocabularly;
        HashMap<String, Integer> positiveCatalog;
        HashMap<String, Integer> negativeCatalog;
        HashMap<String, Double> wordProbabilityPositive;
        HashMap<String, Double> wordProbabilityNegative;
        double probabilityOfClassPositive;
        double probabilityOfClassNegative;
        int totalPositiveWords;
        int totalNegativeWords;

        NaiveBayes naiveBayes = new NaiveBayes();

        File[] posfiles = naiveBayes.getReviews("train/pos");
        File[] negfiles = naiveBayes.getReviews("train/neg");

        //train
        long startTimeTrain = System.nanoTime();
        // Get positive word list
        ArrayList<String> positiveWords = naiveBayes.getWords("train/pos");

        // Get negative word list
        ArrayList<String> negativeWords = naiveBayes.getWords("train/neg");

        // Get instance of HashMap for positive and negative words
        positiveCatalog = naiveBayes.getPositiveWordList();
        negativeCatalog = naiveBayes.getNegativeWordList();

        // create positive and negative catalogs with word frequences
        positiveCatalog = naiveBayes.createCatalog(positiveCatalog, positiveWords);
        negativeCatalog = naiveBayes.createCatalog(negativeCatalog, negativeWords);

        // create a a set for vocabulary
        vocabularly = naiveBayes.getVocabularly();
        vocabularly.addAll(positiveCatalog.keySet());
        vocabularly.addAll(negativeCatalog.keySet());


        wordProbabilityPositive = new HashMap<>();
        wordProbabilityNegative = new HashMap<>();

        totalPositiveWords = positiveWords.size();
        totalNegativeWords = negativeWords.size();

        // Set the probability for a word given the class (Positive, Negative)
        for (int x = 0; x < 2; x++) {
            if (x == 0) {
                naiveBayes.computeWordProbability(wordProbabilityPositive, totalPositiveWords, positiveCatalog, vocabularly);
            } else {
                naiveBayes.computeWordProbability(wordProbabilityNegative, totalNegativeWords, negativeCatalog, vocabularly);
            }
        }

        // Probability of class been positive and negative
        probabilityOfClassPositive = (naiveBayes.getNumberOfPositiveDocuments() / naiveBayes.getNumberOfDocuments());
        probabilityOfClassNegative = (naiveBayes.getNumberOfNegativeDocuments() / naiveBayes.getNumberOfDocuments());
        long stopTimeTrain = System.nanoTime();
        //endoftrain

        //test
        long startTimeTest = System.nanoTime();
        // Get arrays of test reviews
        File[] testReviews = naiveBayes.getReviews("test");
        System.out.println("Train: " + (posfiles.length + negfiles.length) + " documents");
        System.out.println("        Positive: " + posfiles.length + " documents");
        System.out.println("        Negative: " + negfiles.length + " documents");
        System.out.println();
        System.out.println("Test: " + testReviews.length + " documents");
        System.out.println();
        String reviews1 = TestReviewResults(testReviews, probabilityOfClassPositive, probabilityOfClassNegative, naiveBayes, wordProbabilityPositive, wordProbabilityNegative, reviews);
        long stopTimeTest = System.nanoTime();
        //endoftest

        DecimalFormat df = new DecimalFormat("#.####");

        System.out.println();
        System.out.println("Train time: " + df.format((stopTimeTrain - startTimeTrain) / 1000000000.0) + " seconds");
        System.out.println("Test time:" + df.format((stopTimeTest - startTimeTest) / 1000000000.0) + " seconds");
        return reviews1;
    }

    public String TestReviewResults(File[] listOfReviews, double probabilityOfClassPositive, double probabilityOfClassNegative, NaiveBayes naiveBayes, HashMap<String, Double> wordVocabProbabilityPositive, HashMap<String, Double> wordVocabProbabilityNegative, String reviews) throws IOException {

        // HashMap<String, Integer> reviewTestResult = new HashMap<>();

        //         int trueNeg = 0;
        //         int truePos = 0;
        //         double accuracy;
        String[] wordArray;
        String reviewClass;
        ArrayList<String> reviewWordList;
        //FileWriter writer = new FileWriter("Predictions.txt");
        // for(int x = 1; x < listOfReviews.length; x++){

        // String reviewText = naiveBayes.readFile(listOfReviews[x]);
        String reviewText = reviews;
        String results;
        wordArray = reviewText.split("\\s+");
        reviewWordList = new ArrayList<>(Arrays.asList(wordArray));
        reviewClass = naiveBayes.testReviewClassification(reviewWordList, probabilityOfClassPositive, probabilityOfClassNegative, wordVocabProbabilityPositive, wordVocabProbabilityNegative);
        // String [] fileparts = listOfReviews[x].getName().split("\\.");
        //         String filename = fileparts[0]; //Get first part
        //System.out.println("File "+filename);

                        /*writer = new FileWriter("Predictions.txt", true);
                        BufferedWriter bwriter = new BufferedWriter(writer);
                        PrintWriter pwriter = new PrintWriter(bwriter);
                        pwriter.print(filename);
                        pwriter.print("\t\t");*/
        if (reviewClass.equals("Negative")) {
            System.out.println("Negative");
//            reviews.setNegative(true);
            // pwriter.print("0\n");  /*System.out.println(filename+" "+0);*/
            // if ( x <= 100) trueNeg++;
            results= "Negative";
        } else {
            // pwriter.print("1\n");  /*System.out.println(filename+" "+1);*/
            // if ( x > 100) truePos++;
            System.out.println("Positive");
//            reviews.setPositive(true);
            results= "Positive";
        }
        //pwriter.flush();
        //pwriter.close();
        // }
        // DecimalFormat df = new DecimalFormat("#.##");
        // accuracy = trueNeg + truePos;
        // accuracy = accuracy/listOfReviews.length;
        // System.out.println("Accuracy: " + df.format(accuracy));
        return results;
    }

    @RequestMapping("/showfeed")
    public String showFeedack(Model model) {


        List<Positive> positive = positiveRepo.findAll();
        model.addAttribute("feed", positive);
        System.out.println("feedback is " + positive);
        return "DBord";
        // return "lecturelog2";

    }

////feedback controller 
//    @RequestMapping("/ss")
//    public  String ss(Model model,Positive positive){
//
//     List<MessaheInterface> maInterfaces=positiveRepo.findallBycusAr();
//     
//     for (MessaheInterface messaheInterface : maInterfaces) {
//       //  System.out.println("Id- " + messaheInterface.getid() + ", count - " + messaheInterface.getCount());
//         
//     //   int id=messaheInterface.getid();
//     //   String count =messaheInterface.getCount();
//     //    
//       //  System.out.println(id +"  " + count);
//       model.addAttribute("messaheInterface",messaheInterface);
//     }
//    
//         
//
//     
//     return "test";
// 
//    }

    @RequestMapping("/jj")
    public String calljsp() {

        return "myjsp.jsp";
    }


    //leader board controller based on high positive feedbacks
    @RequestMapping("/pos")
    public String getpossss(Model model) {


        List<Positive> positive = positiveRepo.findmypos();


        model.addAttribute("rating", positive);

        //return"test";
        return "leade";
    }


    //chat page mapping mvc
    @RequestMapping("/messenger")
    public String showMessanger() {

        return "Messenger";
    }

    //user chat controller using Spring websocket
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessenger, SimpMessageHeaderAccessor HeaderAccessor) {

        HeaderAccessor.getSessionAttributes().put("username", chatMessenger.getSender());
        return chatMessenger;

    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessenger) {

        return chatMessenger;
    }


    //view of password change  request page 
    @RequestMapping("/passchange")
    public String getpasswordForStudent() {


        return "password3";
    }


    @RequestMapping("/getuserdetails")
    public String changePasswordStudent(@RequestParam String Username, Model model) {


        Students students = studentRepo.findbylog(Username);
        model.addAttribute("data", students);


        return "password3";
    }

    //change password controller
    @RequestMapping("/changestudentpassword")
    public String acseptChangePassword(int id, Students students, Model model) {
        studentRepo.deleteById(id);
        System.out.println("sucess");
        studentRepo.save(students);
        String text = "sucess";

        //model.addAttribute(text, "text");
        model.addAttribute(text);

        model.addAttribute("sucess", "password change successfully");
        return "password3";
    }

    //web user contact massage saving on db controller
    @RequestMapping("/userinq")
    public String sendUserInqry(User_message message) {

        inqrepo.save(message);


        return "contacts.html";
    }


}


