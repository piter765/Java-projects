package com.example._3javafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class EmployeeController {
    //employees table
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, EmployeeCondition> conditionColumn;
    @FXML
    private TableColumn<Employee, Integer> birthYearColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TableColumn<Employee, Void> deleteColumn;

    //employee inputs
    @FXML
    private TextField nameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField birthYearInput;
    @FXML
    private TextField employeeConditionInput;
    @FXML
    private TextField salaryInput;

    //classEmployee Table
    @FXML
    private TableView<ClassEmployee> classEmployeeTable;

    @FXML
    private TableColumn<Employee, String> groupNameColumn;
    @FXML
    private TableColumn<Employee, List<Employee>> employeeListColumn;
    @FXML
    private TableColumn<Employee, Integer> maxNumOfEmployeesColumn;
    @FXML
    private TableColumn<ClassEmployee, Double> occupancyRateColumn;
    @FXML
    private TableColumn<ClassEmployee, Void> deleteClassEmployeeColumn;

    //classEmployee inputs
    @FXML
    private TextField groupNameInput;
    @FXML
    private TextField maxNumOfEmployeesInput;


    @FXML
    protected void onAddButtonClick(ActionEvent event) {
        String nameText = nameInput.getText();
        String lastNameText = lastNameInput.getText();
        String birthYearText = birthYearInput.getText();
        String conditionText = employeeConditionInput.getText();
        String salaryText = salaryInput.getText();

        if (nameText.isEmpty() || lastNameText.isEmpty() || birthYearText.isEmpty() || conditionText.isEmpty() || salaryText.isEmpty()) {
            showErrorPopup("Provide all the fields");
            return;
        }

        String name = nameText;
        String lastName = lastNameText;
        int birthYear = parseInt(birthYearText);
        EmployeeCondition condition = EmployeeCondition.valueOf(conditionText);
        double salary = Double.parseDouble(salaryText);

        Employee newEmployee = new Employee(name, lastName, condition, birthYear, salary);

        employeeTable.getItems().add(newEmployee);

        employeeTable.refresh();

        nameInput.clear();
        lastNameInput.clear();
        birthYearInput.clear();
        employeeConditionInput.clear();
        salaryInput.clear();

    }

    @FXML
    protected void onAddToClassEmployeeButtonClick(ActionEvent event) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        ClassEmployee selectedClassEmployee = classEmployeeTable.getSelectionModel().getSelectedItem();

        try {


            if (selectedEmployee != null && selectedClassEmployee != null) {
                // Add the selected employee to the employee list of the selected ClassEmployee
                selectedClassEmployee.addEmployee(selectedEmployee);

                // Refresh the second table to reflect the changes
                classEmployeeTable.refresh();
            } else {
                throw new Exception("Please select both an employee and a group to add the employee to.");
            }
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    @FXML
    protected void onAddClassEmployeeButtonClick(ActionEvent event) {
        String groupName = groupNameInput.getText();
        String maxNumOfEmployeesText = maxNumOfEmployeesInput.getText();

        if (groupName.isEmpty() || maxNumOfEmployeesText.isEmpty()) {
            showErrorPopup("All fields should be provided");
        }

        int maxNumOfEmployees = parseInt(maxNumOfEmployeesText);

        ClassEmployee classEmployee = new ClassEmployee(groupName, maxNumOfEmployees);

        classEmployeeTable.getItems().add(classEmployee);

        classEmployeeTable.refresh();

        groupNameInput.clear();
        maxNumOfEmployeesInput.clear();
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        birthYearColumn.setCellValueFactory(new PropertyValueFactory<>("rokUrodzenia"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("wynagrodzenie"));

        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaGrupy"));
        employeeListColumn.setCellValueFactory(new PropertyValueFactory<>("listaPracownikow"));
        maxNumOfEmployeesColumn.setCellValueFactory(new PropertyValueFactory<>("maksymalnaIloscPracownikow"));
        occupancyRateColumn.setCellValueFactory(data -> {
            ClassEmployee classEmployee = data.getValue();
            BigDecimal bd = BigDecimal.valueOf(classEmployee.calculateOccupancy());
            bd = bd.setScale(2, RoundingMode.HALF_UP);

            return new SimpleDoubleProperty(bd.doubleValue()).asObject();
        });


        employeeTable.getItems().add(new Employee("Piotr", "Tymula", EmployeeCondition.OBECNY, 2002, 10000));
        employeeTable.getItems().add(new Employee("Szymon", "A", EmployeeCondition.NIEOBECNY, 2003, 700));
        employeeTable.getItems().add(new Employee("Pawel", "B", EmployeeCondition.OBECNY, 2004, 5000));

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Employee employee = getTableView().getItems().get(getIndex());
                    // Perform your delete action here
                    employeeTable.getItems().remove(employee);

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        deleteClassEmployeeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteClassEmployeeButton = new Button("Delete");

            {
                deleteClassEmployeeButton.setOnAction(event -> {
                    ClassEmployee classEmployee = getTableView().getItems().get(getIndex());
                    // Perform your delete action here
                    classEmployeeTable.getItems().remove(classEmployee);

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteClassEmployeeButton);
                }
            }
        });

        employeeListColumn.setCellFactory(column -> {
            return new TableCell<Employee, List<Employee>>() {
                private final ScrollPane scrollPane = new ScrollPane();
                private final VBox content = new VBox();

                {
                    scrollPane.setContent(content);
                    scrollPane.setFitToWidth(true);
                }

                @Override
                protected void updateItem(List<Employee> employees, boolean empty) {
                    super.updateItem(employees, empty);

                    if (employees == null || empty) {
                        setGraphic(null);
                    } else {
                        // Display employee information in the VBox
                        content.getChildren().clear();
                        for (Employee employee : employees) {
                            Label label = new Label(employee.toString()); // Use toString() or format as needed
                            content.getChildren().add(label);
                        }

                        setGraphic(scrollPane);
                    }
                }
            };
        });

    }

    public static void showErrorPopup(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
