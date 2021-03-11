import { Picture } from "./picture";

export class Post {
   
    constructor(
        private _id: number,
        private _body: string,
        private _picture: Picture
    ){}

    public get picture(): Picture {
        return this._picture;
    }
    public set picture(value: Picture) {
        this._picture = value;
    }
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
}
