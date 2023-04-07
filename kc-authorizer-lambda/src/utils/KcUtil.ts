import { Policy } from "../models/Policy";


export class KcUtil {

    static async validateToken(token: string): Promise<boolean> {
        //TODO: Implement token validation
        return token === 'Bearer 123';
    }

    static async generatePolicy(principalId: string, effect: string, resource: string): Promise<Policy> {
        return {
            principalId: principalId,
            policyDocument: {
                Version: '2012-10-17',
                Statement: [
                    {
                        Action: 'execute-api:Invoke',
                        Effect: effect,
                        Resource: [resource]
                    }
                ]
            },
            context: {
                key: 'value',
                number: 1,
                bool: true
            }
        }
    }
}