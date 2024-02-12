const ITEM_ID_REGEX = /^(P)[0-9]{3}$/;
const ITEM_NAME_REGEX = /^[A-Za-z ]{4,}$/;
const numbersOnlyRegex = /^[0-9]+$/;
const ITEM_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationPrice;
var validationQTY;

// let itemNumberCounter = 1;
// let itemIDstor;
//
// generateItemID();
// function generateItemID() {
//     const itemCode = `P${String(itemNumberCounter).padStart(3, '0')}`;
//     itemNumberCounter++;
//     itemIDstor = itemCode;
//     $("#itemCode").val(itemCode);
// }

// function clearItemInputFields() {
//     $("#itemCode,#itemName,#itemPrice,#itemQty").val("");
//     $("#itemCode,#itemName,#itemPrice,#itemQty").css("border", "1px solid #ced4da");
//     $("#itemCode").focus();
// }


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

$("#itemNameTxt").keyup(function (e) {
    let value = $("#itemNameTxt").val();
    if (value.length == 0) {
        $("#btnSaveItem").attr('disabled',true);
        $("#itemNameTxt").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#itemNameTxt").css('border', '2px solid green');
        } else {
            $("#itemNameTxt").css('border', '2px solid red');
        }
    }});

$("#unitePriceTxt").keyup(function (e) {
    let value = $("#unitePriceTxt").val();
    if (value.length == 0) {
        $("#btnSaveItem").attr('disabled',true);
        $("#unitePriceTxt").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_SALARY_REGEX.test(value);
        if (res) {
            validationPrice=1;
            setBtn();
            $("#unitePriceTxt").css('border', '2px solid green');
        } else {
            $("#unitePriceTxt").css('border', '2px solid red');
        }
    }});

$("#itemQtyTxt").keyup(function (e) {
    let value = $("#itemQtyTxt").val();
    if (value.length == 0) {
        $("#btnSaveItem").attr('disabled',true);
        $("#itemQtyTxt").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            validationQTY=1;
            setBtn();
            $("#btnSaveItem").attr('disabled',false);
            $("#btnUpdateItem").attr('disabled',false);
            $("#itemQtyTxt").css('border', '2px solid green');
        } else {
            $("#itemQtyTxt").css('border', '2px solid red');
        }
    }});


function setBtn() {
    if (validationId==1 && validationName==1 && validationPrice==1 && validationQTY==1){
        $("#btnSaveItem").attr('disabled',false);
        $("#btnUpdateItem").attr('disabled',false);
    }
}
