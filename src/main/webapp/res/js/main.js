/**
 * Created by vladislav on 20.05.14.
 */
var currentYear = (new Date).getFullYear();
var currentMonth = (new Date).getMonth();
var currentQuarter = Math.floor(((currentMonth + 11) / 3) % 4) + 1;
var currentDate = (new Date).getDate(); //day
var fieldStartDate = document.getElementById('startDate');
var fieldEndDate = document.getElementById('endDate');

var getCurrentYear = function(){
    return{
        "start":currentYear+"-01-01",
        "end" : currentYear+"-12-31"
    }
}

var setLastYear = function(){
    document.getElementById('startDate').value = getCurrentYear().start;
    document.getElementById('endDate').value = getCurrentYear().end;
}

var getLastYear = function(){
    return{
        "start": currentYear-1+"-01-01",
        "end": currentYear-1+"-12-31"
    }
}

var setLastYear = function(){
    document.getElementById('startDate').value = getLastYear().start;
    document.getElementById('endDate').value = getLastYear().end;
}

var setCurrentQtr = function(){
    document.getElementById('startDate').value = getCurrentQtr().start;
    document.getElementById('endDate').value = getCurrentQtr().end;
}

var setLastQtr = function(){
    document.getElementById('startDate').value = getLastQtr().start;
    document.getElementById('endDate').value = getLastQtr().end;
}

var getCurrentQtr = function(){
    switch (currentQuarter){
        case 1:{
            return {
                "start": currentYear+"-"+"01-01",
                "end"  : currentYear+"-"+"03-31"
            };
        }
        case 2:{
            return {
                "start": currentYear+"-"+"04-01",
                "end"  : currentYear+"-"+"06-30"
            }
        }
        case 3:{
            return{
                "start": currentYear+"-"+"07-01",
                "end"  : currentYear+"-"+"09-31"
            }
        }
        case 4:{
            return{
                "start": currentYear+"-"+"10-01",
                "end"  : currentYear+"-"+"12-31"
            }
        }
    }
}

var setLastMonth = function(){
    document.getElementById('startDate').value = getLastMonth().start;
    document.getElementById('endDate').value = getLastMonth().end;
}

var getCurrentMonth = function(){
    var x = new Date();
    var fD = new Date(x.getFullYear(), x.getMonth(), 1);
    var eD = new Date(x.getFullYear(), x.getMonth()+1, 0);
    return{
        "start":currentYear+"-"+currentMonth+"-"+fD.getDate(),
        "end": currentYear+"-"+currentMonth+"-"+eD.getDate()
    }
}

var setCurrentMonth = function(){
    document.getElementById('startDate').value = getCurrentMonth()  .start;
    document.getElementById('endDate').value = getCurrentMonth().end;
}


var getLastMonth = function(){
    var x = new Date();
    var fD = new Date(x.getFullYear(), x.getMonth() - 1, 1);
    var lD = new Date(x.getFullYear(), x.getMonth(), 0);
    return {
        "start": fD.getFullYear()+"-"+fD.getMonth()+"-"+fD.getDay(),
        "end"  : lD.getFullYear()+"-"+lD.getMonth()+"-"+lD.getDay()
    }
}
//        $(function() {
//            $( ".datepicker" ).datepicker();
//            $.datepicker.formatDate("dd-mm-yy");
//        });

var getLastQtr = function(){
    switch (currentQuarter -1){
        case 1:{
            return {
                "start": currentYear+"-"+"01-01",
                "end"  : currentYear+"-"+"03-31"
            };
        }
        case 2:{
            return {
                "start": currentYear+"-"+"04-01",
                "end"  : currentYear+"-"+"06-30"
            }
        }
        case 3:{
            return{
                "start": currentYear+"-"+"07-01",
                "end"  : currentYear+"-"+"09-31"
            }
        }
        case 0:{
            return{
                "start": currentYear-1+"-"+"10-01",
                "end"  : currentYear-1+"-"+"12-31"
            }
        }
    }
}

function dateChange(choice) {
//    var choice = document.getElementById('timePeriod');
//    var selectedOption = choice.options[choice.selectedIndex].value;
//    console.log(selectedOption)
//    setLastMonth();
    console.log(choice);
    switch (parseInt(choice)){
        case 1:{
            setLastQtr();
            break;
        }
        case 2:{
            setLastMonth();
            break
        }
        case 3:{
            setLastYear();
            break;
        }
        case 4:{
            break
        }
        case 5:{
            setCurrentQtr();
            break;
        }
        case 6:{
            setCurrentMonth();
            break;
        }
    }
}
