package com.shekharhandigol

data class SalaryBreakdown(
    val basicSalary: Double,
    val hra: Double,
    val conveyanceAllowance: Double,
    val medicalAllowance: Double,
    val otherAllowances: Double,
    val totalEarnings: Double,
    val incomeTax: Double,
    val providentFund: Double,
    val professionalTax: Double,
    val otherDeductions: Double,
    val totalDeductions: Double,
    val netSalary: Double
)

fun calculateSalaryBreakdown(annualIncome: Double): SalaryBreakdown {

    val basicSalary = annualIncome / 12
    val hra = 0.3 * basicSalary
    val conveyanceAllowance = 1000.0
    val medicalAllowance = 500.0
    val otherAllowances = 0.0

    val totalEarnings = basicSalary + hra + conveyanceAllowance + medicalAllowance + otherAllowances

    // Tax slabs for FY 2023-2024 (New Tax Regime)
    val incomeTax = calculateIncomeTax(annualIncome)

    val providentFund = 0.12 * basicSalary

    val professionalTax = 250.0

    val otherDeductions = 0.0

    val totalDeductions = incomeTax + providentFund + professionalTax + otherDeductions

    val netSalary = totalEarnings - totalDeductions

    return SalaryBreakdown(
        basicSalary,
        hra,
        conveyanceAllowance,
        medicalAllowance,
        otherAllowances,
        totalEarnings,
        incomeTax,
        providentFund,
        professionalTax,
        otherDeductions,
        totalDeductions,
        netSalary
    )
}

fun calculateIncomeTax(annualIncome: Double): Double {
    // Income tax slabs for FY 2023-2024 (New Tax Regime)
    val taxSlabs = arrayOf(
        Triple(0.0, 300000.0, 0.0),
        Triple(300001.0, 600000.0, 0.05),
        Triple(600001.0, 900000.0, 0.10),
        Triple(900001.0, 1200000.0, 0.15),
        Triple(1200001.0, 1500000.0, 0.20),
        Triple(1500001.0, Double.MAX_VALUE, 0.30)
    )

    var taxableIncome = annualIncome

    taxableIncome -= 50000.0        // Standard deduction

    var incomeTax = 0.0
    for (slab in taxSlabs) {
        val taxableAmount = if (taxableIncome > slab.second) {
            slab.second - slab.first
        } else {
            maxOf(0.0, taxableIncome - slab.first)
        }
        incomeTax += taxableAmount * slab.third
        taxableIncome -= taxableAmount
        if (taxableIncome <= 0) {
            break
        }
    }

    return incomeTax
}