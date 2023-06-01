import { APIGatewayTokenAuthorizerHandler } from "aws-lambda";
import { createLogger, format, transports } from 'winston';
import { KcUtil } from "./utils/KcUtil";
import { PolicyPayload } from "./models/PolicyPayload";

const AUTH_AWS_REGION = process.env.AUTH_AWS_REGION;
const AUTH_AWS_ACCOUNT_ID = process.env.AUTH_AWS_ACCOUNT_ID;


const logger = createLogger({
    level: 'info',
    transports: [
        new transports.Console({
            format: format.combine(
                format.printf(info => {
                    const {message} = info;
                    const formattedJson = JSON.stringify(message, null, 2); // especifica 2 espacios para la identación
                    return `${info.level}: ${formattedJson}`;
                })
            )
        })
    ]
});

export const handler: APIGatewayTokenAuthorizerHandler = async (event, context) => {
    logger.info('Event: ' + JSON.stringify(event));
    logger.info('Context: ' + JSON.stringify(context));
    const token = event.authorizationToken;
    const payload: PolicyPayload = {
        principalId: 'user|kcatucuamba',
        resource: event.methodArn,
        effect: 'Allow'
    };

    if (!KcUtil.validateToken(token)) {
        payload.effect = 'Deny';
    }
    
    const policy = await KcUtil.generatePolicy(payload);
    logger.info('Policy: ' + JSON.stringify(policy));
    console.log('Policy (Log): ' + JSON.stringify(policy));
    return policy;
}