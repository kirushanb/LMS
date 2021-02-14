$(document).ready(function () {
    $(' .nBtn, .table .eBtn').on('click',function(event) {
        event.preventDefault();
        var href =$(this).attr('href');
        var text = $(this).text();
        if (text=='Edit') {

            $.get(href, function (Marks, status) {
                $('.myForm #marksid').val(Marks.marksid);
                $('.myForm #marks').val(Marks.marks);
                $('.myForm #id').val(Marks.id);
                $('.myForm #sid').val(Marks.sid);


            });

            $('.myForm #exampleModal').modal();
        }else {

            $('.myForm #marksid').val('');
            $('.myForm #marks').val('');
            $('.myForm #id').val('');
            $('.myForm #sid').val('');

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