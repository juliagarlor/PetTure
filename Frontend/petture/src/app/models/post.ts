import { Picture } from "./picture";

export class Post {
   
    constructor(
        private _id: number,
        private _body: string,
        private _pictureId: number,
        private _userName: string,
        private _licks: number
    ){}

    public get body(): string {
        return this._body;
    }
    public set body(value: string) {
        this._body = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get userName(): string {
        return this._userName;
    }
    public set userName(value: string) {
        this._userName = value;
    }
    public get pictureId(): number {
        return this._pictureId;
    }
    public set pictureId(value: number) {
        this._pictureId = value;
    }

    public get licks(): number {
        return this._licks;
    }
    public set licks(value: number) {
        this._licks = value;
    }
}
