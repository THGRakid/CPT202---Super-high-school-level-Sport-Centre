function addRow(){
    varchartable= document.getElementById('table')
    var length=table.rows.length;
   var newRow=table.insertRow(length);
  
   var idCol=newRow.insertCell(0);
   var nameCol=newRow.insertCell(1); 
   var EmailCol=newRow.insertCell(2);
   var passwordCol=newRow.insertCell(3);
   var sexCol=newRow.insertCell(4);
   var phoneCol=newRow.insertCell(5);
   var avatarCol=newRow.insertCell(6);
   var ResCol=newRow.insertCell(7);
   var OpCol=newRow.insertCell(8);

   idCol.innerHTML='---'
   nameCol.innerHTML='---'
   EmailCol.innerHTML='---' 
   passwordCol.innerHTML='---'
   sexCol.innerHTML='---'
   phoneCol.innerHTML='---'
   avatarCol.innerHTML='#'
   ResCol.innerHTML='---'
   OpCol.innerHTML='<button onclick="EditRow(this)">Edit</button> <button onclick="DelRow(this)">Delete</button>'
   
}

function DelRow(button){
    var row=button.parentNode.parentNode;
    row.parentNode.removeChild(row);
}

function EditRow(button){
    var row=button.parentNode.parentNode;
    var id = row.cells[0];
    var name = row.cells[1]; 
    var email = row.cells[2];
    var password = row.cells[3];
    var sex = row.cells[4];
    var phone = row.cells[5];
    var res = row.cells[7];
    
    var input1 =prompt("PleaseEnter userID:");
    var input2 =prompt("PleaseEnter Name:"); 
    var input3 =prompt("PleaseEnter Password:");
    var input4 =prompt("PleaseEnter Email:");
    var input5 =prompt("PleaseEnter Gender:");
    var input6 =prompt("PleaseEnter PhoneNumber:");
    var input7 =prompt("PleaseEnter ResignationTime:");
    
    id.innerHTML=input1;
    name.innerHTML=input2;    
    email.innerHTML=input3;
    password.innerHTML=input4;
    sex.innerHTML=input5;
    phone.innerHTML=input6;
    res.innerHTML=input7;
}
