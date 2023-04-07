import { APIGatewayAuthorizerResult } from "aws-lambda";
import { PolicyPayload } from "../models/PolicyPayload";


export class KcUtil {

    static async validateToken(token: string): Promise<boolean> {
        //TODO: Implement token validation
        return token === 'Bearer 123';
    }

    static async generatePolicy(payload: PolicyPayload): Promise<APIGatewayAuthorizerResult> {
        const policy: APIGatewayAuthorizerResult = {
            principalId: payload.principalId,
            policyDocument: {
                Version: '2012-10-17',
                Statement: [
                    {
                        Action: 'execute-api:Invoke',
                        Effect: payload.principalId,
                        Resource: [payload.resource]
                    }
                ]
            },
            context: {
                key: 'value',
                number: 1,
                bool: true
            }
        }
        return policy;
    }
}