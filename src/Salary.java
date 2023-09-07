class InvalidSalaryException extends Exception {
    InvalidSalaryException(String str) {
        super(str);
    }
}

public class Salary {
    float basic;
    float da;
    float hra;
    float gross;
    float net;
    boolean isTeaching=false;

    Salary(float basic, boolean isTeaching) throws InvalidSalaryException {
        if (basic<0)
            throw new InvalidSalaryException("Invalid Basic Pay : Basic Pay cannot be less than Rs. 0!");
        else {
            this.isTeaching = isTeaching;
            this.basic = basic;
            this.da = (float) ((35.0/100.0)*this.basic);
            this.hra = (float) (15.0/100.0*this.basic);
            this.gross = this.basic + this.da + this.hra;
            this.net = (this.gross>500000) ? (float) (this.gross + 25.0 / 100.0 * this.gross) : 0;
        }
    }

    void display() {
        System.out.printf(" Rs. %-17.2fRs. %-17.2f", this.basic, this.gross);
        if (this.isTeaching) {
            System.out.printf("Rs. %-17.2f", this.net);
        }
    }

}
