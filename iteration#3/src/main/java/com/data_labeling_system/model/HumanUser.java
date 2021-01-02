package com.data_labeling_system.model;

import com.data_labeling_system.mechanism.LabelingMechanismFactory;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class HumanUser extends User implements Parsable {

    private String username;
    private String password;

    public HumanUser(String json) {
        parse(json);
        LabelingMechanismFactory labelingMechanismFactory = new LabelingMechanismFactory();
        mechanism = labelingMechanismFactory.makeLabelingMechanism(type);
        logger = Logger.getLogger(HumanUser.class);
        logger.info("Created '" + name + "' as '" + type + "'.");
    }

    @Override
    public void parse(String json) {
        // Parse the User json using org.json library
        JSONObject object = new JSONObject(json);
        id = object.getInt("user id");
        name = object.getString("user name");
        type = object.getString("user type");
        consistencyCheckProbability = object.getDouble("consistencyCheckProbability");
        username = object.getString("username");
        password = object.getString("password");
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}