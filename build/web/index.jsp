<%@page import="utils.SourceCodes"%>
<%
    String lastnames=null;
    String names=null;
    String mail=null;
    String username=null;
    String password=null;
    HttpSession sesionOK=request.getSession();
    if(sesionOK.getAttribute("username")!=null && sesionOK.getAttribute("mail")!=null){
        lastnames=(String)sesionOK.getAttribute("lastnames");
        names=(String)sesionOK.getAttribute("names");
        mail=(String)sesionOK.getAttribute("mail");
        username=(String)sesionOK.getAttribute("username");
        password=(String)sesionOK.getAttribute("password");
    }
    if(sesionOK.getAttribute("username")==null){
%>
        <jsp:forward page="login.jsp"/>
<%
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Red Social</title>
        <link rel="stylesheet" href="css/style_index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/v4-shims.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Fjalla+One&display=swap" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script src="js/script_index.js"></script>
    </head>
    <body onload="bodyLoadEvent()">
        <div class="container">
            <nav class="nav-main">
                
                <img src="img/logo.PNG" alt="RedSocial logo" class="nav-icon">
                <label class="search">
                    <input dir="ltr" id="id_search" class="search-friend" aria-autocomplete="list" aria-expanded="false" aria-label="Buscar amigos" type="search" role="combobox" spellcheck="false" placeholder="Buscar amigos" onkeyup="search()">
                </label>
                <div class="log-out">
                    <i class="fa fa-sign-out" aria-hidden="true"></i>
                    <a href="sv_main?acjsp=log-out">Cerrar Sesión</a>
                </div>
            </nav>

            <hr>
            <div class="container-search" id="id_container-search">
                <ul class="ul-users" id="myUL">
                    <%
                    for(int i=0;i<SourceCodes.getListUsers("").size();i++){
                        %>
                        <li><a href="sv_main?acjsp=<%=(i+1)%>">»  <%=SourceCodes.getListUsers("").get(i)%></a></li>
                     <%   
                    }
                    %>
                </ul>
            </div>
            <div class="container-list_friends">
                <div class="title-list_friends">
                    <h3 class="title">Lista de amigos</h3>
                </div>
                <div class="items-list_friends">
                    <p> Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem, ducimus rerum? Aperiam ducimus cum laboriosam qui quam est sed fugiat molestias fuga explicabo! Voluptatum dolor molestias odit pariatur tempore fugit expedita, est illum nobis totam iure sint ullam repudiandae ipsam. Sint maiores nam nemo optio perferendis voluptas id. Tempora, id.
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem, ducimus rerum? Aperiam ducimus cum laboriosam qui quam est sed fugiat molestias fuga explicabo! Voluptatum dolor molestias odit pariatur tempore fugit expedita, est illum nobis totam iure sint ullam repudiandae ipsam. Sint maiores nam nemo optio perferendis voluptas id. Tempora, id.</p>
                </div>
            </div>
            <div class="box_chat">

            </div>
            <div class="info_friend">
                
            </div>
        </div>
    </body>
</html>
