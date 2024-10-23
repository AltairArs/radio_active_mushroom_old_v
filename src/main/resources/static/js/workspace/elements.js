import * as vars from "./vars.js";

export let workspace = {
    "this": document.getElementById(vars.WORKSPACE_ID),
    "menu": {
        "this": document.getElementById(vars.WORKSPACE_MENU_ID),
        "options": {
            "addTable": {
                "dialog": document.getElementById(vars.WORKSPACE_ADD_TABLE_DIALOG_ID),
                "showButton": document.getElementById(vars.WORKSPACE_ADD_TABLE_BUTTON_ID),
                "submitButton": document.getElementById(vars.WORKSPACE_ADD_TABLE_BUTTON_SUBMIT_ID)
            },
            "connectTables": {
                "dialog": document.getElementById(vars.WORKSPACE_CONNECT_TABLES_DIALOG_ID),
                "showButton": document.getElementById(vars.WORKSPACE_CONNECT_TABLES_BUTTON_ID)
            }
        }
    },
    "content": document.getElementById(vars.WORKSPACE_CONTENT_ID)
};

export function getCSRF() {
    return {
        "token": document.getElementsByName(vars.CSRF_TOKEN_ID).item(0).value,
        "header": document.getElementsByName(vars.CSRF_HEADER_NAME_ID).item(0).value
    };
}
