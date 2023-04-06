import Axios from "axios"

export const httpClient = Axios.create({
    baseURL: "https://zhj834bu8l.execute-api.us-east-1.amazonaws.com/v1/labs",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
    }
});