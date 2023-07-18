import Axios from "axios"

export const httpClient = Axios.create({
    baseURL: "https://n9zr3b7c3m.execute-api.us-east-1.amazonaws.com/v1/labs/",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
        // "x-request-id": "1050309275",
        "Authorization": "Bearer eyJraWQiOiJNVmVkcXVudUFocUtlMVNTUWhUMFdZSzFFZXY2TlowUnVOc1pCVDAxYVwvMD0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiNDU4NzRlOC0zMDIxLTcwNGQtN2NkMC1hNTE4M2M2NzRiZWMiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV83VzZYMG42djAiLCJjbGllbnRfaWQiOiJlN2szNGUxbTVpaWJhNjl0YWJsdmxnbDBqIiwib3JpZ2luX2p0aSI6ImNkZTYxZTMzLTE5OTUtNGEyZS04ZGUyLWU1YTE3YTgzNTI5OCIsImV2ZW50X2lkIjoiMDc1NTU2ZmQtODM3Ny00Y2IyLWExMDYtZDVkZmE5ZGE4ZDRkIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJhd3MuY29nbml0by5zaWduaW4udXNlci5hZG1pbiIsImF1dGhfdGltZSI6MTY4OTY0NTcxOCwiZXhwIjoxNjg5NjQ5MzE4LCJpYXQiOjE2ODk2NDU3MTgsImp0aSI6Ijk2YzkxYWY3LTVmZWUtNDlmNS04Yjg0LTQ1YWE4OGMzODAxNCIsInVzZXJuYW1lIjoia2NhdHVjdWFtYmEifQ.LhTy0rjVA3lmcbq-AY0u8_tfMkMKkxrAhjr9jgoyvEqH0gQFQEsl59M0zDROXVaTB7Wab-C8G5ukxcspFKg5LZjUWzgJ11ETPc3IzHmoONxfskZ5TY_iQygbfDOH-5vifsgbVi_k0X33Vb8giijLgMd4TuT5KXxJIBfQPr7U0c2gQYUmUbHlgbuwoFumdZcO6hfsMlUWkxv0xW2Z6KucCm4w2iBw4xXQu6H9FQ8ywsnJSRMy858Vw3UDSM2UwjXeMK2rCa3Ia-Hp8Hg6ZnIhvzlqHVruwpmsaWPI4OpjXD_mNMb6uXPM-wpfMUstDgSrrVyqD2ADsvyYN45O41llAQ",
        "x-api-key": "pfX1BCRag75dFzq62kDBo4zz7ySbRMyV1yGBCn7q",
    }
});