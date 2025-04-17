package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	private Employee() {
		childNames = new LinkedList<>();
		childIdNumbers = new LinkedList<>();
	}
	
	/**
	 * Builder class untuk membangun objek Employee dengan parameter yang ringkas namun mengorbankan baris yang menjadi lebih panjang
	 */

	public static class EmployeeBuilder {
		private String employeeId;
		private String firstName;
		private String lastName;
		private String idNumber;
		private String address;
		private int yearJoined;
		private int monthJoined;
		private int dayJoined;
		private boolean isForeigner;
		private boolean gender;

		public EmployeeBuilder setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
			return this;
		}

		public EmployeeBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public EmployeeBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public EmployeeBuilder setIdNumber(String idNumber) {
			this.idNumber = idNumber;
			return this;
		}

		public EmployeeBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public EmployeeBuilder setJoinedDate(int year, int month, int day) {
			this.yearJoined = year;
			this.monthJoined = month;
			this.dayJoined = day;
			return this;
		}

		public EmployeeBuilder setIsForeigner(boolean isForeigner) {
			this.isForeigner = isForeigner;
			return this;
		}

		public EmployeeBuilder setGender(boolean gender) {
			this.gender = gender;
			return this;
		}

		public Employee build() {
			Employee emp = new Employee();
			emp.employeeId = this.employeeId;
			emp.firstName = this.firstName;
			emp.lastName = this.lastName;
			emp.idNumber = this.idNumber;
			emp.address = this.address;
			emp.yearJoined = this.yearJoined;
			emp.monthJoined = this.monthJoined;
			emp.dayJoined = this.dayJoined;
			emp.isForeigner = this.isForeigner;
			emp.gender = this.gender;
			return emp;
		}
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
