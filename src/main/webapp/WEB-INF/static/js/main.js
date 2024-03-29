/**
 * Created by Serhiy on 3/25/2015.
 */
var apiNewsAddress = "/api/news";
var debugMode = true;

var offset = 0;
var quantity = 1;

var leftColumn;
var rightColumn;

var newsResponse;
//--------------- Debug data ---------------------
if(debugMode) {
    newsResponse =
        '{' +
        '   "news":[' +
        '       {' +
        '           "news_id":"10234",' +
        '           "program_id":"11",' +
        '           "program_logo":"http://upload.wikimedia.org/wikipedia/commons/3/33/Vanamo_Logo.png",' +
        '           "program_name":"Допоможемо Лесі вивчити Java",' +
        '           "date":"24 березня 2015",' +
        '           "text":"Вже давно відомо, що читабельний зміст буде заважати зосередитись людині, яка оцінює композицію сторінки.",' +
        '           "img":"http://upload.wikimedia.org/wikipedia/commons/8/84/Leaning_Tower_of_Pisa_(April_2012).jpg"' +
        '       },' +
        '       {' +
        '           "news_id":"10233",' +
        '           "program_id":"12",' +
        '           "program_logo":"http://upload.wikimedia.org/wikipedia/commons/3/33/Vanamo_Logo.png",' +
        '           "program_name":"Волонтерська програма охорини студмістечка НТУУ КПІ",' +
        '           "date":"23 березня 2015",' +
        '           "text":"Довгий текст новини.",' +
        '           "img":"http://cdn.history.com/sites/2/2013/12/new-york-city-H.jpeg"' +
        '       },' +
        '       {' +
        '           "news_id":"10232",' +
        '           "program_id":"12",' +
        '           "program_logo":"http://upload.wikimedia.org/wikipedia/commons/3/33/Vanamo_Logo.png",' +
        '           "program_name":"Донорство крові в Києві",' +
        '           "date":"22 березня 2015",' +
        '           "text":"Вже давно відомо, що читабельний зміст буде заважати зосередитись людині, яка оцінює композицію сторінки. Сенс використання Lorem Ipsum полягає в тому, що цей текст має більш-менш нормальне розподілення літер на відміну від, наприклад, Тут іде текст. Тут іде текст. Це робить текст схожим на оповідний. Багато програм верстування та веб-дизайну використовують Lorem Ipsum як зразок і пошук за терміном lorem ipsum відкриє багато веб-сайтів, які знаходяться ще в зародковому стані. Різні версії Lorem Ipsum зявились за минулі роки, деякі випадково, деякі було створено зумисно (зокрема, жартівливі).",' +
        '           "img":"http://freshadda.com/images_adda/Cobra-Tower-Kuwait/Cobra%20Tower%20infosys.jpg"' +
        '       }' +
        '   ]' +
        '}';
}
//------------------------------------------------


function loadData() {
    if(!debugMode){
    //    todo: ajax for data
    }
}
function initColumns() {
    leftColumn  = $('#first-column');
    rightColumn = $('#second-column');
}
function createNews(value) {
    var result = '<div class="panel panel-default">' +
        '<div class="panel-heading">' +
        '<p class="hidden info-news-id">' + value.news_id + '</p>' +
        '<p class="hidden info-program-id">' + value.program_id + '</p>' +
        '<img class="img-circle volunteer-program-logo" src="'+ value.program_logo +'" width="60" height="60">' +
        '<div class="volunteer-program-title">' +
        '<div class="name-container">' +
        '<h4 class="volunteer-program-name">'+ value.program_name +'</h4>' +
        '</div>' +
        '<div class="bottom-title">' +
        '<h5 class="volunteer-program-date visible-lg-inline-block visible-md-inline-block visible-sm-inline-block visible-xs-inline-block">'+ value.date +'</h5>' +
        '<div class="horizontal-right-margin">' +
        '<img class="visible-lg-inline-block visible-md-inline-block visible-sm-inline-block visible-xs-inline-block" src="img/subscribe-fill.png">' +
        '<a class="margin-0 visible-lg-inline-block visible-md-inline-block visible-sm-inline-block subscribe-link">Підписатися</a>' +
        '</div>' +
        '<div class="horizontal-right">' +
        '<img class="visible-lg-inline-block visible-md-inline-block visible-sm-inline-block visible-xs-inline-block" src="img/share-fill.png">' +
        '<a class="margin-0 visible-lg-inline-block visible-md-inline-block visible-sm-inline-block share-link">Поділитися</a>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="panel-body">' +
        '<p>' +
        value.text +
        '</p>' +
        '<img src="' + value.img + '" class="img-responsive content-img">' +
        '</div>' +
        '</div>' +
        '</div>';
    return result;
}
function fillContent(){
    loadData();
    initColumns();
    var jsonData = $.parseJSON(newsResponse);

    function appendNews(value) {
        var displayNone = $(rightColumn).attr('display');
        if (typeof displayNone !== typeof undefined && displayNone !== false) {
            leftColumn.append(value);
        } else{
            console.log("here2");
            console.log("left col:" + leftColumn.height());
            console.log("right col:" + rightColumn.height());
            if(leftColumn.height() <= rightColumn.height()) {
                leftColumn.append(value);
            } else {
                rightColumn.append(value);
            }
        }
    }

    $.each(jsonData.news, function (index, value) {
        appendNews(createNews(value));
    });
}

$( document ).ready(function() {
    fillContent();

    $('.name-container').css("width", parseInt($('#panel-heading').width())-80+"px");
    $('.bottom-title').css('width', $('.name-container').width()+"px");
});