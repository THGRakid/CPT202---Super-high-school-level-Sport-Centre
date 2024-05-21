function addRow(){
    varchartable= document.getElementById('table')
    var length=table.rows.length;
   var newRow=table.insertRow(length);
  
   var idCol=newRow.insertCell(0);
   var nameCol=newRow.insertCell(1); 
   var passwordCol=newRow.insertCell(2);
   var EmailCol=newRow.insertCell(3);
   var powerCol=newRow.insertCell(4);
   var HireCol=newRow.insertCell(5);
   var ResCol=newRow.insertCell(6);
   var OpCol=newRow.insertCell(7);

   idCol.innerHTML='---'
   nameCol.innerHTML='---'
   passwordCol.innerHTML='---'
   powerCol.innerHTML='---'
   EmailCol.innerHTML='---'
   HireCol.innerHTML='---'
   ResCol.innerHTML='#'
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
    var password = row.cells[2];
    var email = row.cells[3];
    var power = row.cells[4];
    var hire = row.cells[5];
    var res = row.cells[6];
    
    var input1 =prompt("PleaseEnter AdminID:");
    var input2 =prompt("PleaseEnter Name:"); 
    var input3 =prompt("PleaseEnter AdminPassword:");
    var input4 =prompt("PleaseEnter Email:");
    var input5 =prompt("PleaseEnter PowerLevel:");
    var input6 =prompt("PleaseEnter HireTime:");
    var input7 =prompt("PleaseEnter ResignationTime:");
    
    id.innerHTML=input1;
    name.innerHTML=input2;    
    password.innerHTML=input3;
    email.innerHTML=input4;
    power.innerHTML=input5;
    hire.innerHTML=input6;
    res.innerHTML=input7;
}
