import Axios from "axios"

export const httpClient = Axios.create({
    baseURL: "https://n9zr3b7c3m.execute-api.us-east-1.amazonaws.com/v1/labs/",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "x-request-id": "1050309275",
        "Authorization": "Bearer eyJraWQiOiJNVmVkcXVudUFocUtlMVNTUWhUMFdZSzFFZXY2TlowUnVOc1pCVDAxYVwvMD0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiNDU4NzRlOC0zMDIxLTcwNGQtN2NkMC1hNTE4M2M2NzRiZWMiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV83VzZYMG42djAiLCJjbGllbnRfaWQiOiJlN2szNGUxbTVpaWJhNjl0YWJsdmxnbDBqIiwib3JpZ2luX2p0aSI6ImFkZTAwNjYxLTY3YTQtNGYwZS04YThlLTE2NjIyYjQ0OWRlNSIsImV2ZW50X2lkIjoiNWE4Nzc5NWMtMTI4Yi00MzhlLThlYjUtZThiOWM3NzUyM2RkIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJhd3MuY29nbml0by5zaWduaW4udXNlci5hZG1pbiIsImF1dGhfdGltZSI6MTY4OTczMDgxNywiZXhwIjoxNjg5NzM0NDE3LCJpYXQiOjE2ODk3MzA4MTcsImp0aSI6IjJjODYxZTAwLThjN2ItNGE2MS1iM2NkLTY5YTM1ZDA5NDI4NCIsInVzZXJuYW1lIjoia2NhdHVjdWFtYmEifQ.kw4oZA5UU3cd-cskU7OKPYCbhA-ebK2P7VGWAdketIgZBHoXXhYyQJ08c3ASZm3squPcrUdgex_LK_FLv02SE94-bcnVEZvaCKmliWXphkqisarrC3-lIKLtBqUPxtC7TQ5lZZHKhd26Ph6NipBMRC29sj7OiOXQ6tnxMuO-MGLzp0j6d0F1WhafN4xgx_RRvcBWyRMDVmFSSFmc6fF2YX_UX0wAf1lDZgAHfO2RAgbJNhI42NzHO9Ur6yFcAAZUqrPx-8EjbfAUd9XZMu0Cp7ir4kF5dXrt0ZcUT6woqaqGGbDEIxkIWzkPZaaFD8cogNmr_o61ggr-CNM27K2XEw",
        "x-api-key": "pfX1BCRag75dFzq62kDBo4zz7ySbRMyV1yGBCn7q",
        // "x-ip-address": "10.50.30.9275"
    }
});