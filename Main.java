class Main{
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SignUp signUpForm = new SignUp();
                signUpForm.setVisible(true);
            }   
        });
    }
}