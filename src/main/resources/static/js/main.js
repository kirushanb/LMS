/**
 * 
 */
$(document).ready(function(){
	
	$('.table .eBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href,function(stu,status){
			$('.myForm #id').val(stu.id);
			$('.myForm #Name').val(stu.Name);
			$('.myForm #Email').val(stu.Email);
			$('.myForm #Username').val(stu.Username);
			$('.myForm #Password').val(stu.Password);
			
		});
		$('.myForm #exampleModal').modal();
		
	});
	
	$('.table .delBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#myModal #delRef').attr('href',href);
		$('#myModal').modal();
		
	});
});