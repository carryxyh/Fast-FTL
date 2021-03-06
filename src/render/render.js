/**
 * Created by june on 2017/1/23.
 */
import Client from './client';
import Events from 'events';
import {warning} from '../util';

export default class Render extends Events {
    constructor({port}) {
        super();
        this.client = new Client({port: port});
        this.client.on('recieved', info => {
            this.emit(Render._getParsedEventName(info.template), info);
        });
    }

    parse(template, data) {
        // Hack windows
        template = template.replace(/\\/g, '/');
        this.client.request(
            JSON.stringify({template, data})
        );
        return new Promise((resolve, reject) => {
            this.once(Render._getParsedEventName(template), ({error, content}) => {
                if (error) {
                    warning(error);
                    return reject(error);
                }
                resolve(content || '');
            });
        });
    }

    static _getParsedEventName(tpl) {
        return `Parsed:${tpl}`
    }
}
