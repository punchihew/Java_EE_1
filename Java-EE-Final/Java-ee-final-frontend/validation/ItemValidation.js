const ITEM_ID_REGEX = /^(P)[0-9]{3}$/;
const ITEM_NAME_REGEX = /^[A-Za-z ]{4,}$/;
const numbersOnlyRegex = /^[0-9]+$/;
const ITEM_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationAddress;
var validationSalary;


$("#btnSaveItem").attr('disabled',true);
$("#btnUpdateItem").attr('disabled',true);

$("#itemCodeTxt").keyup(function (e) {
    let value = $("#itemCodeTxt").val();
    if (value.length == 1) {
        $("#btnSaveItem").attr('disabled',true);
        $("#itemCodeTxt").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#itemCodeTxt").css('border', '2px solid green');
        } else {
            $("#itemCodeTxt").css('border', '2px solid red');
        }
    }});

$("#itemName").keyup(function (e) {
    let value = $("#itemName").val();
    if (value.length == 0) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemName").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#itemName").css('border', '2px solid green');
        } else {
            $("#itemName").css('border', '2px solid red');
        }
    }});

$("#itemPrice").keyup(function (e) {
    let value = $("#itemPrice").val();
    if (value.length == 0) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemPrice").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_SALARY_REGEX.test(value);
        if (res) {
            validationAddress=1;
            setBtn();
            $("#itemPrice").css('border', '2px solid green');
        } else {
            $("#itemPrice").css('border', '2px solid red');
        }
    }});

$("#itemQty").keyup(function (e) {
    let value = $("#itemQty").val();
    if (value.length == 0) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemQty").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            validationSalary=1;
            setBtn();
            $("#itemQty").css('border', '2px solid green');
        } else {
            $("#itemQty").css('border', '2px solid red');
        }
    }});


function setBtn() {
    if (validationId==1 && validationName==1 && validationAddress==1 && validationSalary==1){
        $("#btnItemAdd").attr('disabled',false);
        $("#btnItemUpdate").attr('disabled',false);
    }
}
