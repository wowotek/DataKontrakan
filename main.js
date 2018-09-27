const electron = require('electron');
const url = require('url');
const path = require('path');

require('electron-reload')(__dirname);

const {
    app, BrowserWindow, Menu
} = electron;

let mainWindow;
let addWindow;
var loggedIn = false;

// Listen for app to be ready
app.on('ready', function(){
    // Create new Window
    mainWindow = new BrowserWindow({
        frame: true,

        width: 1366,
        height: 768
    });
    // Load HTML file into window
    if(loggedIn){
        mainWindow.loadURL(url.format({
            pathname: path.join(__dirname, 'mainWindow.html'),
            protocol: 'file:',
            slashes: true
        }));
    } else {
        mainWindow.loadURL(url.format({
            pathname: path.join(__dirname, 'login_screen.html'),
            protocol: 'file:',
            slashes: true
        }));
    }
    // Quit app when closed
    mainWindow.on('closed', function(){
        app.quit();
    });

    //mainWindow.setFullScreen(false);
    Menu.setApplicationMenu(null);
});
