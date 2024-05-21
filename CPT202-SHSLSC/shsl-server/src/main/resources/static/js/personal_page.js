function addRow(){
    varchartable= document.getElementById('content7')
    var length=table.rows.length;
   var newRow=table.insertRow(length);
  
   var NameCol=newRow.insertCell(0);
   var CardNumberCol=newRow.insertCell(1); 
   var PhoneNumberCol=newRow.insertCell(2);
   var AddressCol=newRow.insertCell(3);
}
function DelRow(button){
    var row=button.parentNode.parentNode;
    row.parentNode.removeChild(row);
}

function EditRow(button){
    var row=button.parentNode.parentNode;
    var Name = row.cells[0];
    var CardNumber = row.cells[1]; 
    var PhoneNumber = row.cells[2];
    var Address = row.cells[3];
    
    var input1 =prompt("PleaseEnter Name:");
    var input2 =prompt("PleaseEnter CardNumber:"); 
    var input3 =prompt("PleaseEnter PhoneNumber:");
    var input4 =prompt("PleaseEnter Address:");
    
    Name.innerHTML=input1;
    CardNumber.innerHTML=input2;    
    PhoneNumber.innerHTML=input3;
    Address.innerHTML=input4;
}