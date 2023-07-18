import { httpClient } from "./util/httpClient";

const btn1 = document.getElementById('btn1') as HTMLButtonElement;
btn1.addEventListener('click', async () => {
    const response = await httpClient.get('balances/2');
    console.log(response);
    console.log(httpClient)
});