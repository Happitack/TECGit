const express = require("express");
const axios = require("axios");
const { getAll, update, insert } = require("../dataservice/subscriber-service");

const router = express.Router();


router.get("/add", (req, res) => {
    res.render("add-subscriber");
});

function isValidInput(input) {
    const emailRx = /^\w+@\w+\.(com|dk|\w{2,5})$/;
    const telRx = /^\+\d{1,3}\s\d{8,14}$/;
    const nameRX = /^[\p{L}a-zA-ZÆØÅæøå]+( [\p{L}a-zA-ZÆØÅæøå]+)+$/;

    let errors = [];

    for (const [key, value] of Object.entries(input)) {9
        switch (key) {
            case "fullname":
                if (!nameRX.test(value)) {
                    errors.push(`Fulde navn skal være indholde mindst 5 tegn, og have et mellemrum mellem fornavn og efternavn`);
                }
                break;
            case "username":
                if (value.length < 3) {
                    errors.push(`Brugernavn skal være mindst 3 tegn`);
                }
                break;
            case "email":
                if (!emailRx.test(value)) {
                    errors.push(`Email er ikke gyldig`);
                }
                break;
            case "tel":
                if (value && !telRx.test(value)) {
                    errors.push(`Telefonnummer er ikke gyldig. Skal indholde landekode og mellemrum mellem landekode og telefonnummer`);
                }
                break;
        }
    }
    return errors;
}

router.post("/add", async (req, res) => {
    const validationErrors = isValidInput(req.body);

    if(validationErrors.length > 0) {
        res.render("add-subscriber", { errors: validationErrors, inputData: req.body });
        return;
    }

    const data = await insert(req, res);
    if (data.err) {
        console.log(data.err);
        res.render("error", { err: data.err });
    } else {
        res.redirect("/subscriber");
    }
});

router.get("/edit/:id", async (req, res) => {
    const id = req.params.id;
    // if(!Number.isInteger(123.456))
    if (isNaN(id)) {
        res.render("error", { err: "Paremeter id er ikke et tal" });
        return;
    }
    const result = await axios.get("http://localhost:3000/test/" + id);
    res.render("edit-subscriber", { data: result.data });
});

router.post("/edit/:id", async (req, res) => {
    const validationErrors = isValidInput(req.body);

    if (validationErrors.length > 0) {
        // re-render the edit-subscriber page with the error messages and the input data
        res.render("edit-subscriber", { errors: validationErrors, data: req.body, id: req.params.id });
        return;
    }

    const data = await update(req, res);
    if (data.err) {
        console.log(data.err);
        res.render("error", { err: data.err });
    } else {
        res.redirect("/subscriber");
    }
});

router.get("/delete/:id", async (req, res) => {
    const id = req.params.id
    const result = await axios.delete("http://localhost:3000/test/" + id)
    res.redirect("/subscriber");
})

router.get("/", async (req, res) => {
    const data = await getAll(req, res);
    res.render("subscriber", {data});
});

module.exports = router;