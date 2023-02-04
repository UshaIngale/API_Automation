package util;

//import com.agilecrm.types.CustomDataDto;
import com.agilecrm.types.company.CompanyPropertyDto;
import com.agilecrm.types.contact.ContactPropertyDto;
import com.agilecrm.types.deal.CustomDataDto;

import java.util.*;

public class Utility {
    public boolean verifyEmail(String email) {
        boolean valid = true;
        //@##@!!!!@gmail.com   ---> ['#', '@','#','@', '!', '!, '!', '!' , '@', 'g', 'm', 'a', 'i', 'l', '.', 'c', 'o', 'm' ]
        if (email != null && !email.isEmpty()) {  //  if(Objects.isNull(email))
            char[] array = email.toCharArray();
            List<Character> duplicateChar = new ArrayList<>();
            for (char c : array) {
//                    System.out.println("Missing Email Id for Contact : " + contactName);
                if (c == '@') {
                    duplicateChar.add(c);
                }
            }
            if (duplicateChar.size() > 1 || duplicateChar.size() < 1) {
                // System.out.println("Invalid Email Id: " + email);
                valid = false;
            } else {
                String[] emailArr = email.split("@");
                if (emailArr.length != 2)
                    valid = false;
                if (emailArr[0].length() == 0 || emailArr[1].length() < 3)
                    valid = false;
                if (!emailArr[1].contains("."))
                    valid = false;
                if (!Character.isLetter(array[0]))
                    valid = false;
                for (char c : array) {
                    if (!Character.isLetter(c) && !Character.isDigit(c) && c != '_' && c != '.' && c != '@')
                        valid = false;
                }
                if (email.contains("..") || email.contains(".@") || email.contains("@.") || email.contains("._."))
                    valid = false;
                if (email.endsWith("."))
                    valid = false;
            }
        } else {
            System.out.println("Email Id is blank : " + email);
            valid = false;
        }
        return valid;
    }

    public String generateRandomEmail(int num) {
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < num; i++) {
            Random random = new Random();
            int number = random.nextInt(10);
            randomNum.append(number);
        }
        String randomEmail = "usha"+randomNum + "@yopmail.com";
        return randomEmail;
    }
    public List<Long> setContactListForDeal(Map<String, String> data){
        List<Long> contactIdList = new ArrayList<>();
        if (Objects.nonNull(data.get("contactIds"))) {
            String[] contactIds = data.get("contactIds").split(",");
            for (String id : contactIds) {
                long contactId = Long.parseLong(id);
                contactIdList.add(contactId);
            }
        }
        return  contactIdList;
    }

    public List<CustomDataDto> setCustomDataForDeal(Map<String, String> data){
        //prepare custom data object
        List<CustomDataDto> customData = new ArrayList<CustomDataDto>();
        CustomDataDto customDataDto= new CustomDataDto();

        if (Objects.nonNull(data.get("customData"))) {
            String[] dataObject = data.get("customData").split(",");
            customDataDto.setName(dataObject[0]);
            customDataDto.setValue(dataObject[1]);
            customData.add(customDataDto);
        }
        return customData;
    }
    public ContactPropertyDto setpropertyData(String value, String name, String type, String subType){
        ContactPropertyDto propertyDto1=new ContactPropertyDto();
        propertyDto1.setName(name);
        propertyDto1.setType(type);
        propertyDto1.setValue(value);
        propertyDto1.setSubtype(subType);
        return propertyDto1;
    }
    public CompanyPropertyDto setproperty(String value, String name, String type, String subType){
        CompanyPropertyDto propertyDto1=new CompanyPropertyDto();
        propertyDto1.setName(name);
        propertyDto1.setType(type);
        propertyDto1.setValue(value);
        propertyDto1.setSubtype(subType);
        return propertyDto1;
    }
}
