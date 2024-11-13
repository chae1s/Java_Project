package org.example.resume.model;

public class Career {
    private String period;
    private String companyName;
    private String jobDuties;
    private String jobTenure;

    public Career() {
    }

    public Career(String period, String companyName, String jobDuties, String jobTenure) {
        this.period = period;
        this.companyName = companyName;
        this.jobDuties = jobDuties;
        this.jobTenure = jobTenure;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDuties() {
        return jobDuties;
    }

    public void setJobDuties(String jobDuties) {
        this.jobDuties = jobDuties;
    }

    public String getJobTenure() {
        return jobTenure;
    }

    public void setJobTenure(String jobTenure) {
        this.jobTenure = jobTenure;
    }

    @Override
    public String toString() {
        return "Career{" +
                "period='" + period + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobDuties='" + jobDuties + '\'' +
                ", jobTenure='" + jobTenure + '\'' +
                '}';
    }
}
