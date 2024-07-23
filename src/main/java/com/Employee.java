package com;

public class Employee {


        private int NumEmp;
        private String Nom;
        private int Nombrejour;
        private double taux_jounaliere;


        private double Salaire;

        public Employee(int numEmp, String nom, int nombrejour, double taux_jounaliere,double salaire) {
            NumEmp = numEmp;
            Nom = nom;
            Nombrejour = nombrejour;
            this.taux_jounaliere = taux_jounaliere;
            this.Salaire=salaire;
        }

        public int getNumEmp() {
            return NumEmp;
        }

        public void setNumEmp(int numEmp) {
            NumEmp = numEmp;
        }

        public String getNom() {
            return Nom;
        }

        public void setNom(String nom) {
            Nom = nom;
        }

        public int getNombrejour() {
            return Nombrejour;
        }

        public void setNombrejour(int nombrejour) {
            Nombrejour = nombrejour;
        }

        public double getTaux_jounaliere() {
            return taux_jounaliere;
        }

        public void setTaux_jounaliere(double taux_jounaliere) {
            this.taux_jounaliere = taux_jounaliere;
        }
            public double getSalaire() {
            return Salaire;
        }

        public void setSalaire(double salaire) {
            Salaire = salaire;
        }




}
