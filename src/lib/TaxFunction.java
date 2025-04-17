package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
		int taxableIncome = annualIncome - deductible - nonTaxableIncome;

		int tax = (int) Math.round(0.05 * taxableIncome);

		return tax < 0 ? 0 : tax;
	}

	private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
		return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int base = 54000000;
		if (isMarried) {
			base += 4500000;
		}
		base += numberOfChildren * 1500000;
		return base;
	}
	
}
