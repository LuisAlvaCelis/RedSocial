package mvc.model;

public class NewUser {
    
    private String lastnames;
    private String names;
    private String mail;
    private String username;
    private String password;
    
    public NewUser(String lastnames,String names,String mail,String username,String password){
        this.lastnames=lastnames;
        this.names=names;
        this.mail=mail;
        this.username=username;
        this.password=password;
    }
    
    public String getLastnames(){
        return lastnames;
    }
    
    public String getNames(){
        return names;
    }
    
    public String getMail(){
        return mail;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
}
