$(document).ready(function () {
   $(' .nBtn, .table .eBtn').on('click',function(event) {
       event.preventDefault();
       var href =$(this).attr('href');
       var text = $(this).text();
       if (text=='Edit') {

           $.get(href, function (student, status) {
               $('.myForm #id').val(student.id);
               $('.myForm #Name').val(student.name);
               $('.myForm #Email').val(student.email);
               $('.myForm #Username').val(student.username);
               $('.myForm #Password').val(student.password);

           });

           $('.myForm #exampleModal').modal();
       }else {

           $('.myForm #id').val('');
           $('.myForm #Name').val('');
           $('.myForm #Email').val('');
           $('.myForm #Username').val('');
           $('.myForm #Password').val('');
           $('.myForm #exampleModal').modal();

       }
   });
   $('.table .delBtn').on('click',function (event) {
       event.preventDefault();
       var href =$(this).attr('href');
       $('#myModal #delRef').attr('href',href);
       $('#myModal').modal();
       
   });
});