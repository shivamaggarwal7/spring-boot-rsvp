package com.rsvp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.rsvp.entity.Registrant;
import com.rsvp.repository.RegistrantRepository;

@SpringBootApplication
public class RsvpApplication implements WebMvcConfigurer {

	static Logger log = LogManager.getLogger();

	private static RegistrantRepository regRepo;

	@Autowired
	public void setRegRepo(RegistrantRepository regRepo) {
		RsvpApplication.regRepo = regRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(RsvpApplication.class, args);
		readRegistrants();
	}

	/**
	 * Reading list of registrants from spreadsheet using Apache POI and saving to
	 * DB using JPA
	 */
	public static void readRegistrants() {
		/* Reading Excel file for registrants */
		try {
			InputStream excelFile = RsvpApplication.class.getResourceAsStream("/com/rsvp/registrant_list.xlsx");
			log.info("trying???" + excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				if (currentRow.getRowNum() == 0)
					continue;
				Iterator<Cell> cellIterator = currentRow.iterator();

				Registrant user = new Registrant();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					switch (currentCell.getColumnIndex()) {
					case 0:
						user.setDateOfInt(currentCell.getStringCellValue());
						break;
					case 1:
						user.setFirstName(currentCell.getStringCellValue());
						break;
					case 2:
						user.setLastName(currentCell.getStringCellValue());
						break;
					case 3:
						user.setDob(currentCell.getDateCellValue().toString());
						break;
					case 4:
						user.setEmailId(currentCell.getStringCellValue());
						break;
					case 5:
						currentCell.setCellType(CellType.STRING);
						user.setPhoneNo(currentCell.getStringCellValue());
						break;
					case 6:
						user.setCity(currentCell.getStringCellValue());
						break;
					case 7:
						user.setLang(currentCell.getStringCellValue());
						break;
					}
				}
				regRepo.save(user);
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			log.error("The given file could not be found!");
		}
		catch(IOException e)
		{
			log.error("There was some error reading the file!");
		}
		/* Reading Excel file for registrants */
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
