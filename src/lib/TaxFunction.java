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

	private static final int BASE_NON_TAXABLE = 54000000;
	private static final int MARRIED_ALLOWANCE = 4500000;
	private static final int CHILD_ALLOWANCE = 1500000;
	private static final int MAX_CHILDREN = 3;
	private static final double TAX_RATE = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

		validateInput(numberOfMonthWorking);

		numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

		int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
		int taxableIncome = annualIncome - deductible - nonTaxableIncome;

		return calculateTaxFromIncome(taxableIncome);
	}

	private static void validateInput(int numberOfMonthWorking) {
		if (numberOfMonthWorking > 12) {
			throw new IllegalArgumentException("Jumlah bulan bekerja tidak boleh lebih dari 12");
		}
	}

	private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
		return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int total = BASE_NON_TAXABLE;
		if (isMarried) {
			total += MARRIED_ALLOWANCE;
		}
		total += numberOfChildren * CHILD_ALLOWANCE;
		return total;
	}

	private static int calculateTaxFromIncome(int taxableIncome) {
		if (taxableIncome <= 0) return 0;
		return (int) Math.round(TAX_RATE * taxableIncome);
	}
}
