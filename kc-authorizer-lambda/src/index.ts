import { APIGatewayProxyHandler, APIGatewayProxyResult } from "aws-lambda";
import * as log4js from 'log4js';
import { Policy } from "./models/Policy";
import { KcUtil } from "./utils/KcUtil";

const AUTH_AWS_REGION = process.env.AUTH_AWS_REGION;
const AUTH_AWS_ACCOUNT_ID = process.env.AUTH_AWS_ACCOUNT_ID;

const logger = log4js.getLogger();
logger.level = 'debug';

export const handler = async (event: any, context: any): Promise<Policy> => {
    logger.info('Event: ', event);
    logger.info('Context: ', context);
    console.log('event: ', event);
    console.log('context: ', context);
    logger.info('Starting AuthorizerFunctionHandler');
    logger.info('AWS Region: ', AUTH_AWS_REGION);
    logger.info('AWS Account ID: ', AUTH_AWS_ACCOUNT_ID);
    const token = event.headers.Authorization;
    const resource = "arn:aws:execute-api:" + AUTH_AWS_REGION + ":" + AUTH_AWS_ACCOUNT_ID + ":*/*/*/*";
    if(token !== 'Bearer zHJlZXR5') {
        logger.info('Unauthorized');
        return await KcUtil.generatePolicy('user', 'Deny', resource);
    }
    logger.info('Authorized');
    return await KcUtil.generatePolicy('user', 'Allow', resource);
}