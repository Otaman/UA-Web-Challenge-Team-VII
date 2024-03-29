/**
 * Created by Serhiy on 3/29/2015.
 */
$("#file-1").fileinput({
    uploadUrl: '#', // you must set a valid URL here else you will get an error
    allowedFileExtensions : ['jpg', 'png'],
    overwriteInitial: false,
    maxFileSize: 1000,
    maxFilesNum: 10,
    slugCallback: function(filename) {
        return filename.replace('(', '_').replace(']', '_');
    }
});

$("#file-2").fileinput({
    uploadUrl: '#', // you must set a valid URL here else you will get an error
    allowedFileExtensions : ['jpg', 'png'],
    maxFileSize: 1000,
    maxFilesNum: 10,
    slugCallback: function(filename) {
        return filename.replace('(', '_').replace(']', '_');
    }
});

$(".btn-warning").on('click', function() {
    if ($('#file-4').attr('disabled')) {
        $('#file-4').fileinput('enable');
    } else {
        $('#file-4').fileinput('disable');
    }
});
$(".btn-info").on('click', function() {
    $('#file-4').fileinput('refresh', {previewClass:'bg-info'});
});

$('#add-news').on('click', function() {
    $('#add-news').addClass('active');
    $('#add-program').removeClass('active');
    $('#add-admin').removeClass('active');
    $('#add-news-content').removeClass('hidden');
    $('#add-program-content').addClass('hidden');
    $('#add-admin-content').addClass('hidden');
});

$('#add-program').on('click', function() {
    $('#add-news').removeClass('active');
    $('#add-program').addClass('active');
    $('#add-admin').removeClass('active');
    $('#add-news-content').addClass('hidden');
    $('#add-program-content').removeClass('hidden');
    $('#add-admin-content').addClass('hidden');
});

$('#add-admin').on('click', function() {
    $('#add-news').removeClass('active');
    $('#add-program').removeClass('active');
    $('#add-admin').addClass('active');
    $('#add-news-content').addClass('hidden');
    $('#add-program-content').addClass('hidden');
    $('#add-admin-content').removeClass('hidden');
});

$(document).ready(function() {

});
