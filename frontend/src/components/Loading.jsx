import { ClipLoader } from "react-spinners";

function Loading() {

    return (

        <div className="flex justify-center items-center h-64">

            <ClipLoader
                color="#2563eb"
                size={55}
            />

        </div>

    );

}

export default Loading;