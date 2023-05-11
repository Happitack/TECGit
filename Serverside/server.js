const express = require('express');
const PORT = process.env.PORT || 5000;
const NODE_ENV = process.env.NODE_ENV || 'production';
const AXIOS = require('axios');
const squiglines = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"

const app = express();

app.set('view engine', 'pug');

app.use(express.static("public"));


app.get('/', (req, res) => {
    res.render('home');
});

app.get('/about', (req, res) => {
    res.render("about");
});

app.get('/subscriber', async (req, res) => {
    const response = await AXIOS.get("http://localhost:3000/test");
    const data = response.data;
    res.render("subscriber", {data});
});
    

app.listen(PORT, () => {
    let msg = squiglines;
    msg += new Date().toLocaleTimeString("da-DK", {hour: '2-digit', minute: '2-digit'}) + ":" ;
    msg += ` Server started in ${NODE_ENV} mode. Go to http://localhost:${PORT}\n`;
    msg += squiglines;
    console.log(msg);
});