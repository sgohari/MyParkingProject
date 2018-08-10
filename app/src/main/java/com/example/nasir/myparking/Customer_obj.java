package com.example.nasir.myparking;


    public class Customer_obj {

        //Customer Fields

        private int customerID;
        private String userName, password, firstName, lastName, address, postalCode;

        //Construtor
        public Customer_obj(){}
        public Customer_obj(int customerID, String userName, String password, String firstName, String lastName, String address, String postalCode)
        {
            this.customerID = customerID; this.userName = userName;
            this.password = password; this.firstName = firstName;
            this.lastName = lastName; this.address = address;
            this.postalCode = postalCode;
        }
        //setter
        public void setCustomerID(int customerID){this.customerID = customerID; }
        public void setuserName(String userName){this.userName = userName; }
        public void setpassword(String password){this.password = password; }
        public void setfirstName(String firstName){this.firstName = firstName; }
        public void setlastName(String lastName){this.lastName = lastName; }
        public void setaddress(String address){this.address = address; }
        public void setpostalCode (String postalCode){this.postalCode = postalCode; }
        //getter
        public String getUserName() {
            return userName;
        }

    }


