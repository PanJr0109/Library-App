class ReviewRequestModel{
    rating: number;
    bookId: number;
    reviewDescription?:string;
    constructor(rating: number, bookId: number, reviewDesription: string){
        this.rating = rating;
        this.bookId= bookId;
        this.reviewDescription= reviewDesription;
    }
}

export default ReviewRequestModel;