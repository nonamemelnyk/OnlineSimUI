package com.keepcode.testprj.ui.container;

import com.keepcode.testprj.exception.TestprjServiceRuntimeException;
import com.keepcode.testprj.exception.TestprjUIRuntimeException;
import com.keepcode.testprj.model.api.entity.Country;
import com.keepcode.testprj.model.api.entity.Phone;
import com.keepcode.testprj.service.DataService;
import com.keepcode.testprj.converter.ui.CountryTableConvertor;
import com.keepcode.testprj.converter.ui.PhoneTableConvertor;
import com.keepcode.testprj.converter.ui.TableConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

import static com.keepcode.testprj.util.error.ErrorHierarchyWrapper.anyRequestLoggerAndChecker;

public class UIMain {

    private static final Logger log = LoggerFactory.getLogger(UIMain.class);
    private JPanel panel;
    private JTable datatable;
    private JButton phoneButton;
    private JButton countryButton;
    private JTextArea exceptionArea;
    private JTextField countryValue;

    private final DataService dataService;
    private final TableConvertor<Phone> phoneTableConvertor;
    private final TableConvertor<Country> countryTableConvertor;

    public UIMain(DataService dataService) {
        this.dataService = dataService;
        phoneTableConvertor = new PhoneTableConvertor();
        countryTableConvertor = new CountryTableConvertor();
        phoneButtonPrepare();
        countryButtonPrepare();
    }

    public JPanel getPanel() {
        return panel;
    }


    public void phoneButtonPrepare() {
        phoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String textFieldValue = countryValue.getText();
                    Integer countryId = textFieldValue != null && !textFieldValue.isEmpty() ? Integer.parseInt(textFieldValue) : null;

                    Supplier<List<Phone>> supplier = () -> countryId != null
                            ? dataService.loadPhoneDataService(countryId) : dataService.loadPhoneDataService();
                    List<Phone> phones = anyRequestLoggerAndChecker(supplier, TestprjServiceRuntimeException::new);

                    Vector<String> names = phoneTableConvertor.getColumnNames();
                    Vector<Vector<Object>> data = phoneTableConvertor.getData(phones);
                    DefaultTableModel model = new DefaultTableModel(data, names);
                    datatable.setModel(model);
                } catch (Exception e) {
                    exceptionArea.setText(e.getClass().toString() + ":" + e.getMessage());
                    log.error("UI exception: " + e.getMessage(), e);
                    throw new TestprjUIRuntimeException(e);
                }
            }
        });
    }

    public void countryButtonPrepare() {
        countryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    List<Country> phones = anyRequestLoggerAndChecker(dataService::loadCountryDataService,
                            TestprjServiceRuntimeException::new);
                    Vector<String> names = countryTableConvertor.getColumnNames();
                    Vector<Vector<Object>> data = countryTableConvertor.getData(phones);
                    DefaultTableModel model = new DefaultTableModel(data, names);
                    datatable.setModel(model);
                } catch (Exception e) {
                    exceptionArea.setText(e.getClass().toString() + ":" + e.getMessage());
                    log.error("UI exception: " + e.getMessage(), e);
                    throw new TestprjUIRuntimeException(e);
                }
            }
        });
    }
}
