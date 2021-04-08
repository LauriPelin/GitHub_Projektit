package fi.utu.tech.ThreadRunner.UI;

import java.io.IOException;

import fi.utu.tech.ThreadRunner.App;
import fi.utu.tech.ThreadRunner.dispatchers.ControlSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/*
 * Käyttöliittymän kontrolleriluokka. Luokkaa ei esitelle tarkemmin. Näihin tutustutaan Käyttöliittymät-kurssilla.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class MainWindowController {
	@FXML
	private ComboBox<String> taskTypeComboBox;
	@FXML
	private ComboBox<String> workerTypeComboBox;
	@FXML
	private Spinner<Integer> amountTasksSpinner;
	@FXML
	private Spinner<Integer> maxTimeSpinner;
	@FXML
	private Spinner<Integer> threadCountSpinner;
	@FXML
	private ImageView taskImage;

	@FXML
	private TableView<StatisticLine> staticTable;
	@FXML
	private TableColumn<StatisticLine, String> staticTaskTypeColumn;
	@FXML
	private TableColumn<StatisticLine, String> staticWorkerTypeColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> staticAmountTaskColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> staticMaxTimeColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> staticThreadCountColumn;
	@FXML
	private TableColumn<StatisticLine, Float> staticTimeTypeColumn;

	@FXML
	private TableView<StatisticLine> dynamicTable;
	@FXML
	private TableColumn<StatisticLine, String> dynamicTaskTypeColumn;
	@FXML
	private TableColumn<StatisticLine, String> dynamicWorkerTypeColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> dynamicAmountTaskColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> dynamicMaxTimeColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> dynamicThreadCountColumn;
	@FXML
	private TableColumn<StatisticLine, Float> dynamicTimeTypeColumn;

	@FXML
	private TableView<StatisticLine> wotTable;
	@FXML
	private TableColumn<StatisticLine, String> wotTaskTypeColumn;
	@FXML
	private TableColumn<StatisticLine, String> wotWorkerTypeColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> wotAmountTaskColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> wotMaxTimeColumn;
	@FXML
	private TableColumn<StatisticLine, Integer> wotThreadCountColumn;
	@FXML
	private TableColumn<StatisticLine, Float> wotTimeTypeColumn;

	private final ObservableList<StatisticLine> staticData = FXCollections.observableArrayList();

	private final ObservableList<StatisticLine> dynamicData = FXCollections.observableArrayList();

	private final ObservableList<StatisticLine> wotData = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		initSpinners();
		initTables();
	}

	private void initSpinners() {
		threadCountSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Runtime.getRuntime().availableProcessors()));
		threadCountSpinner.getValueFactory().setValue(Integer.valueOf(2));
		maxTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000));
		maxTimeSpinner.getValueFactory().setValue(Integer.valueOf(10));
		amountTasksSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000));
		amountTasksSpinner.getValueFactory().setValue(Integer.valueOf(20));
	}

	private void initTables() {
		staticTaskTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, String>("taskType"));
		staticWorkerTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, String>("workerType"));
		staticAmountTaskColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("amountTasks"));
		staticMaxTimeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("maxTime"));
		staticThreadCountColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("threadCount"));
		staticTimeTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Float>("time"));

		staticTable.setItems(staticData);

		dynamicTaskTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, String>("taskType"));
		dynamicWorkerTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, String>("workerType"));
		dynamicAmountTaskColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("amountTasks"));
		dynamicMaxTimeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("maxTime"));
		dynamicThreadCountColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("threadCount"));
		dynamicTimeTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Float>("time"));

		dynamicTable.setItems(dynamicData);

		wotTaskTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, String>("taskType"));
		wotWorkerTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, String>("workerType"));
		wotAmountTaskColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("amountTasks"));
		wotMaxTimeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("maxTime"));
		wotThreadCountColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Integer>("threadCount"));
		wotTimeTypeColumn.setCellValueFactory(new PropertyValueFactory<StatisticLine, Float>("time"));

		wotTable.setItems(wotData);

	}

	@FXML
	private void runStatic() throws IOException {
		App.runStatic(new ControlSet(taskTypeComboBox.getValue(), workerTypeComboBox.getValue(),
				maxTimeSpinner.getValue(), threadCountSpinner.getValue(), amountTasksSpinner.getValue()));
	}

	@FXML
	private void runDynamic() throws IOException {
		App.runDynamic(new ControlSet(taskTypeComboBox.getValue(), workerTypeComboBox.getValue(),
				maxTimeSpinner.getValue(), threadCountSpinner.getValue(), amountTasksSpinner.getValue()));

	}

	@FXML
	private void runWot() throws IOException {
		App.runWot(new ControlSet(taskTypeComboBox.getValue(), workerTypeComboBox.getValue(), maxTimeSpinner.getValue(),
				threadCountSpinner.getValue(), amountTasksSpinner.getValue()));

	}

	@FXML
	public void fillTaskType(String[] list) throws IOException {
		taskTypeComboBox.getItems().addAll(list);
		taskTypeComboBox.getSelectionModel().selectFirst();
	}

	@FXML
	public void fillWorkerType(String[] list) throws IOException {
		workerTypeComboBox.getItems().addAll(list);
		workerTypeComboBox.getSelectionModel().select("SleepWorker");
		;
	}

	@FXML
	public void fillMaxTimeSpinner() throws IOException {
		maxTimeSpinner.getValueFactory().setValue(Integer.valueOf(50));

	}

	@FXML
	public void addStaticResult(StatisticLine result) throws IOException {
		staticData.add(result);

	}

	@FXML
	public void addDynamicResult(StatisticLine result) throws IOException {
		dynamicData.add(result);

	}

	@FXML
	public void addWotResult(StatisticLine result) throws IOException {
		wotData.add(result);

	}
}
