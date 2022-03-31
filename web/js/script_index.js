
/*window.onmousedown = function(e){
    if(e.target.id != 'id_search'){
        document.getElementById("id_search").value="";
        document.getElementById("myUL").style.display = "none";
        return;
    }
};*/
function bodyLoadEvent(){
    document.getElementById("myUL").style.display = "none"; 
}
function search() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('id_search');
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName('li');
    if(document.getElementById('id_search').value === ""){
        document.getElementById("myUL").style.display = "none"; 
    }else{
        document.getElementById("myUL").style.display = "block";
        for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("a")[0];
            txtValue = a.textContent || a.innerText;
            if(txtValue.toUpperCase().indexOf(filter) > -1) {
                li[i].style.display = "";
            } else {
                li[i].style.display = "none";
            }
        }
    }
}