$(document).ready(function(){
    $("#image_preview").hide();
    $('#uploadFile').on('change', function() {
        ext = $(this).val().split('.').pop().toLowerCase(); //확장자
        //배열에 추출한 확장자가 존재하는지 체크
        if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
            resetFormElement($(this)); //폼 초기화
            window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
        } else {
            file = $('#uploadFile').prop("files")[0];
            console.log(file);
            blobURL = window.URL.createObjectURL(file);
            $('#image_preview img').attr('src', blobURL);
            $("#image_preview").show();
           // $('#image_preview').slideDown(); //업로드한 이미지 미리보기
           // $(this).slideUp(); //파일 양식 감춤
        }
    });
});