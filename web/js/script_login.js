var inputs = document.getElementsByClassName('form_input');
for (var i = 0; i < inputs.length; i++) {
    inputs[i].addEventListener('keyup', function(){
        if(this.value.length>=1) {
            this.nextElementSibling.classList.add('fijar');
        } else {
            this.nextElementSibling.classList.remove('fijar');
        }
    });
}

function seePassword(){
    var txt=document.getElementById("txtPass");
    if(txt.type==="password"){
        txt.type="text";
    }else{
        txt.type="password";
    }
}