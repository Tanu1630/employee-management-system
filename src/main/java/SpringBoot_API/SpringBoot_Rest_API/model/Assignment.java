package SpringBoot_API.SpringBoot_Rest_API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assignmentId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull(message = "Employee must not be null")
    private Employee employee;

    @NotBlank(message = "Project Name must not be blank")
    @Size(min = 2, max = 100, message = "Project Name must be between 2 and 100 characters")
    private String projectName;

    @NotBlank(message = "Team Name must not be blank")
    @Size(min = 2, max = 100, message = "Team Name must be between 2 and 100 characters")
    private String teamName;

    @NotBlank(message = "Position must not be blank")
    @Size(min = 2, max = 50, message = "Position must be between 2 and 50 characters")
    private String position;

    @Min(value = 1, message = "Duration must be at least 1 month")
    private int durationInMonths;

    public Assignment() {
    }

    // Getters and Setters

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }
}
