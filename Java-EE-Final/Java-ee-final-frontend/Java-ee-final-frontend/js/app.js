
loadAllItems();
loadAllCus();

function loadAllCus(){

    $.ajax({
        url : "http://localhost:8080/app/customers",
        success : function(res){
            let customers = $(res);
            $('#tblBody').empty();

            for(let i = 0; i < customers.length; i++){
                let id = customers[i].id;
                let name = customers[i].name;
                let address = customers[i].address;
                let salary = customers[i].salary;

                let row =`<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${salary}</td></tr>`;
                $('#tblBody').append(row);
            }
        }
    });

}


function Getall(){
    $('#tblBody').empty();
    $.ajax({
        url : "http://localhost:8080/app/customers",
        method : "GET",
        success : function (resp) {
            // console.log("Success: ", resp);
            console.log(resp[0])
            for (let i=0; i<resp.length; i++) {

                console.log(resp[i].id);
                console.log(resp[i].name);
                console.log(resp[i].address);
                console.log(resp[i].salary);

                const row = `<tr>
                                <td>${resp[i].id}</td>
                                <td>${resp[i].name}</td>
                                <td>${resp[i].address}</td>
                                <td>${resp[i].salary}</td>
                            </tr>`;
                $('#tblBody').append(row);
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
}


$('#btnGetAll').on("click",function () {
    Getall();
});
$('#btnSave').click(function () {


    const id = $('#customerIdText').val();
    const name = $('#customerNameText').val();
    const address = $('#customerAddressText').val();
    const salary = $('#customerSalaryText').val();

    const customerObj = {
        id:id,
        name:name,
        address:address,
        salary:salary
    };

    const jsonObj = JSON.stringify(customerObj);

    $.ajax({
        url: "http://localhost:8080/app/customers",
        method: "POST",
        data: { id:id,
            name:name,
            address:address,
            salary:salary},

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);

            Getall();

                alert(jqxhr.responseText);
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })

});
$('#btnDelete').click(function () {

    const id = $('#customerIdText').val();

    $.ajax({
        url: "http://localhost:8080/app/customers?id=" + id,
        method: "DELETE",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);

            Getall();
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })


});
$('#btnUpdate').click(function () {


    const id = $('#customerIdText').val();
    const name = $('#customerNameText').val();
    const address = $('#customerAddressText').val();
    const salary = $('#customerSalaryText').val();

    const customerObj = {
        id:id,
        name:name,
        address:address,
        salary:salary
    };

    const jsonObj = JSON.stringify(customerObj);

    $.ajax({
        url: "http://localhost:8080/app/customers",
        method: "PUT",
        data:JSON.stringify(customerObj),

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);

            Getall();
        },
        error: function ( error) {
            console.log("error: ", error);
        }
    })
});

/////////////////////////////////////////////////////////////////////////////////////

function loadAllItems(){

    $.ajax({
        url : "http://localhost:8080/app/item",
        success : function(res){
            let item = $(res);
            $('#itemTblBody').empty();

            for(let i = 0; i < item.length; i++){
                let code = item[i].code;
                let desc = item[i].desc;
                let unitPrice = item[i].unitPrice;
                let qty = item[i].qty;



                let row =`<tr><td>${code}</td><td>${desc}</td><td>${unitPrice}</td><td>${qty}</td></tr>`;
                $('#itemTblBody').append(row);
            }
        }
    });

}
function GetallItem(){

    $('#itemTblBody').empty();

    $.ajax({
        url : "http://localhost:8080/app/item",
        method : "GET",
        success : function (resp) {
            // console.log("Success: ", resp);
            console.log(resp[0])
            for (let i=0; i<resp.length; i++) {

                console.log(resp[i].code);
                console.log(resp[i].desc);
                console.log(resp[i].unitPrice);
                console.log(resp[i].qty);

                const row = `<tr>
                                <td>${resp[i].code}</td>
                                <td>${resp[i].desc}</td>
                                <td>${resp[i].unitPrice}</td>
                                <td>${resp[i].qty}</td>
                            </tr>`;
                $('#itemTblBody').append(row);
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
}

$('#btnItemGetAll').on("click",function () {
    GetallItem();
});

$('#btnSaveItem').click(function () {


    const ItemCode = $('#itemCodeTxt').val();
    const ItemName = $('#itemNameTxt').val();
    const ItemPrice = $('#unitePriceTxt').val();
    const ItemQty = $('#itemQtyTxt').val();

    const itemObj = {
        ItemCode:ItemCode,
        ItemName:ItemName,
        ItemPrice:ItemPrice,
        ItemQty:ItemQty
    };

    const jsonObj = JSON.stringify(itemObj);

    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "POST",
        data: {  code:ItemCode,
                 name:ItemName,
                 price:ItemPrice,
                 qty:ItemQty},

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
            GetallItem();
            alert(jqxhr.responseText);
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })

});

$('#btnRemoveItem').click(function () {

    const code = $('#itemCodeTxt').val();

    $.ajax({
        url: "http://localhost:8080/app/item?code=" + code,
        method: "DELETE",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
            GetallItem();

        },
        error: function (error) {
            console.log("error: ", error);
        }
    })


});

$('#btnUpdateItem').click(function () {


    const code = $('#itemCodeTxt').val();
    const description = $('#itemNameTxt').val();
    const unitPrice = $('#unitePriceTxt').val();
    const qtyOnHand = $('#itemQtyTxt').val();

    const itemObj =
    {
        "code":code,
        "desc": description,
        "unitPrice": unitPrice,
        "qty" : qtyOnHand
    };

    const jsonObj = JSON.stringify(itemObj);

    console.log(jsonObj);
    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "PUT",
        data:jsonObj,

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
            alert("Success !");
            GetallItem();
        },
        error: function ( error) {
            console.log("error: ", error);
        }
    })
});

///////////////////////////////////////////////////////////////////////////////////////////////

let allCustomers;
let allItems;

loadAllCusDet();
loadAllItemDet();

$('#txtCash').val("0");
$('#txtDiscount').val("0");
$('#txtBalance').val("0");

function loadAllCusDet(){
    $.ajax({
        url : "http://localhost:8080/app/customers",
        success : function(res){
            allCustomers = $(res);

            let optionCus;

            for(let i = 0; i < allCustomers.length; i++){
                let id = allCustomers[i].id;
                optionCus += '<option value="' + id + '">' + id + '</option>';
            }

            $('#selectCusID').append(optionCus);
        }
    });
}


function loadAllItemDet(){
    $.ajax({
        url : "http://localhost:8080/app/item",
        success : function(res){
            allItems = $(res);

            let optionItem;

            for(let i = 0; i < allItems.length; i++){
                let code = allItems[i].code;
                optionItem += '<option value="' + code + '">' + code + '</option>';
            }

            $('#selectItemCode').append(optionItem);
        }
    });
}


function getItemDetails() {
    let rows = $("#orderTable").children().length;
    var cart = [];

    for (let i = 0; i < rows; i++) {
        let itCode = $("#orderTable").children().eq(i).children(":eq(0)").text();
        let avQty = $("#orderTable").children().eq(i).children(":eq(3)").text();
        let itQty = $("#orderTable").children().eq(i).children(":eq(4)").text();
        let itPrice = $("#orderTable").children().eq(i).children(":eq(2)").text();
        cart.push({code: itCode, avQty:avQty, qty: itQty, price: itPrice});
    }

    return cart;
}

$('#selectCusID').change(function() {
    let id = $(this).val();

    for(let i = 0; i < allCustomers.length; i++){
        if(allCustomers[i].id == id){
            $('#orderCustomerID').val(allCustomers[i].id);
            $('#orderCustomerName').val(allCustomers[i].name);
            $('#orderCustomerAddress').val(allCustomers[i].address);
            $('#orderCustomerSalary').val(allCustomers[i].salary);
        }
    }
});



$('#selectItemCode').change(function() {
    let code = $(this).val();

    for(let i = 0; i < allItems.length; i++){
        if(allItems[i].code == code){
            $('#txtItemCode').val(allItems[i].code)
            $('#txtItemDescription').val(allItems[i].desc)
            $('#txtItemPrice').val(allItems[i].unitPrice)
            $('#txtQTYOnHand').val(allItems[i].qty)
        }
    }
});




$("#btnAddToTable").click(function () {

    let code = $("#selectItemCode").val();
    let description = $("#txtItemDescription").val();
    let itemPrice = $("#txtItemPrice").val();
    let buyQty = $("#txtQty").val();
    let total = parseFloat(itemPrice) * parseFloat(buyQty);
    $("#orderTable").append(`<tr><td>${code}</td><td>${description}</td><td>${itemPrice}</</td><td>${buyQty}</td><td>${total}</td></tr>`);

    let tot = Number($('#txtCash').val()) + total;
    let tota = Number($('#orderTotal').val()) + total;
    let subTot = Number($('#txtCash').val()) + total - Number($('#txtDiscount').val());


    $('#total').text(tot);
    $('#subtotal').text(subTot);
    $('#orderTotal').text(tota);

});

$("#txtDiscount").on("change paste keyup", function() {

    $("#subtotal").text(parseInt($('#total').text()) - parseInt($("#txtDiscount").val()));

    if(parseInt($("#subtotal").text()) < 0){
        $("#subtotal").text("0");
    }

    $("#txtBalance").val(parseInt($('#txtCash').val()) - parseInt($("#total").text()));

    if(parseInt($("#txtBalance").val()) < 0){
        $("#txtBalance").val("0");
    }

});

$("#txtCash").on("change paste keyup", function() {

    $("#txtBalance").val(parseInt($('#txtCash').val()) - parseInt($("#subtotal").text()));

    if(parseInt($("#txtBalance").val()) < 0){
        $("#txtBalance").val("0");
    }

    $("#subtotal").text(parseInt($('#total').text()) - parseInt($("#txtDiscount").val()));

    if(parseInt($("#subtotal").text()) < 0){
        $("#subtotal").text("0");
    }

});


// function generateOrderId(){
//     if(orderDetails.length == 0){
//         $('#orderIDTxtOrderPage').val(1);
//     }else{
//         $('#orderIDTxtOrderPage').val(orderDetails.length + 2);
//     }
// }

$('#cleroders').click(function (){
    $('#orderTotal').text("0");
    $('#total').text("0");
    $('#subtotal').text("0");
    $('#txtCash').val("");
    $('#txtDiscount').val("");
    $('#txtBalance').val("");
});



$('#btnSubmitOrder').click(function(){

    let orderId = $('#txtOrderID').val();
    let date = $('#txtDate').val();
    let cusId = $('#orderCustomerID').val();
    let itemD = getItemDetails();


    let allData = {
        orderId : orderId,
        date : date,
        cusId : cusId,
        itemDet : itemD
    }

    $.ajax({
        url: "http://localhost:8080/app/placeOrder",
        method: "post",
        dataType: "json",
        data: JSON.stringify(allData),
        contentType: "application/json",
        success: function (resp) {
            let valu = parseInt($('#txtCash').val());
            let ordrtotal = parseInt($('#orderTotal').val());

            $('#txtBalance').val("navishka");
         console.log(allData);
        },
        error: function (error) {

        }
    });

});

















