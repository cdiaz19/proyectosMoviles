/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function doSearch(){
    var tableReg= document.getElementById('datos');
    var SearchText=document.getElementById('searchTerm').value.toLowerCase();
    var cellsofRow= "";
    var CompareWith="";
    
    for(var i=1;tableReg.rows.length;i++)
    {
        cellsofRow=tableReg.rows[i].getElementsByTagName('td');
        found=false;
        for(var j=0;j<cellsofRow.length && !found;j++)
        {
        CompareWith=cellsofRow[j].innerHTML.toLowerCase();
        
        if(SearchText.length==0||(CompareWith.indexOf(SearchText)>-1)){
            found=true;
        }
    }
    if(found){
    tableReg.rows[i].style.display='';
}else{
    tableReg.rows[i].style.display='none';
}
    }
    }
    

